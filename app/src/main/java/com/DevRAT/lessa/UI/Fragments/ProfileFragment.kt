package com.DevRAT.lessa.UI.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.ViewModel.SenasViewModel
import com.DevRAT.lessa.Database.ViewModel.WordViewModel
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Activities.GoogleSingInActivity
import com.DevRAT.lessa.UI.Activities.MainActivity
import com.DevRAT.lessa.UI.Activities.SenaActivity
import com.DevRAT.lessa.UI.Activities.SenaPageViewActivity
import com.DevRAT.lessa.UI.Adapter.SenasAdapter
import com.DevRAT.lessa.firebase.SenaFire
import com.DevRAT.lessa.firebase.Statics
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.welcome_layout.view.*


class ProfileFragment : Fragment() {


    private lateinit var vmW: WordViewModel
    private lateinit var vm: SenasViewModel
    private lateinit var rv: RecyclerView
    private lateinit var observer : Observer<List<Senas>>
    private lateinit var ve : View

    //private var _db: DatabaseReference = FirebaseDatabase.getInstance().reference.child("seb")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    companion object {
        var auth: GoogleSignInAccount? = null
        var  conext :Context? =null
        fun newInstance(authy: GoogleSignInAccount?,context : Context): ProfileFragment = ProfileFragment().apply {
            auth = authy
            conext = context


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false).apply {


            ve = this

            vm = ViewModelProviders.of(conext as MainActivity).get(SenasViewModel::class.java)
            rv = recycle_view_profile
            vm.load(MainActivity.usery)
            observer = Observer{
                updateRecycler(it)
            }
            SenasViewModel.senass.observe(conext as LifecycleOwner, observer)


            //MainActivity.viewModelUser!!.load()
            //Log.d("com.DevRAT.lessa", SenaViewModel.senass?.value.toString())

            actividad_session_lanzar.setOnClickListener {
                startActivityForResult(Intent(conext, GoogleSingInActivity::class.java).putExtra("CODIGO",1), 1)
            }
            boton_salir_google.setOnClickListener {
                startActivityForResult(Intent(conext, GoogleSingInActivity::class.java).putExtra("CODIGO",2), 2)
            }
            if(GoogleSignIn.getLastSignedInAccount(conext)!=null){
                val account =GoogleSignIn.getLastSignedInAccount(conext)
                actividad_session_lanzar2.text = account?.email.toString()//auth?.email.toString()
                actividad_session_lanzar.visibility =View.GONE
                boton_salir_google.visibility =View.VISIBLE
                actividad_session_lanzar2.visibility = View.VISIBLE
                Glide.with(this).load(account?.photoUrl.toString()).into(contenedor_de_foto_perfil)
                //val fragment = favoritosList.newInstance()
                //fragmentManager!!.beginTransaction().replace(R.id.recycle_view_profile,fragment).addToBackStack("").commit()

            }
            else{
                actividad_session_lanzar.visibility =View.VISIBLE//auth?.email.toString()
                boton_salir_google.visibility =View.INVISIBLE
                actividad_session_lanzar2.visibility = View.INVISIBLE
                contenedor_de_foto_perfil.visibility = View.GONE//Glide.with(this).load(FirebaseAuth.getInstance().currentUser?.providerData?.get(0)?.photoUrl.toString()).into(contenedor_de_foto_perfil)

            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Toast.makeText(conext, "Cai aqui :v" ,            Toast.LENGTH_LONG).show()

        if(resultCode==1){
            //--------------si la session se inicia correctamente----------------
            val account =GoogleSignIn.getLastSignedInAccount(conext)
            actividad_session_lanzar2.text = account?.email.toString()
            MainActivity.usery = account?.email.toString()
            actividad_session_lanzar.visibility =View.GONE
            boton_salir_google.visibility =View.VISIBLE
            actividad_session_lanzar2.visibility = View.VISIBLE
            Glide.with(this).load(account?.photoUrl.toString()).into(contenedor_de_foto_perfil)
            contenedor_de_foto_perfil.visibility = View.VISIBLE
            MainActivity.viewModelUser!!.load()
            val handler =  ve.handler.postDelayed(Runnable {
                vm.load(account?.email.toString())
            }, 500)

            //--------------------------------------------------------------------
        }
        else{
            //--------------si algo falla al iniciar session----------------------
            actividad_session_lanzar.visibility =View.VISIBLE//auth?.email.toString()
            boton_salir_google.visibility =View.GONE
            actividad_session_lanzar2.visibility = View.GONE
            MainActivity.usery = "default"
            contenedor_de_foto_perfil.visibility = View.GONE
            MainActivity.viewModelUser!!.load()
            val handler =  ve.handler.postDelayed(Runnable {
                vm.load("default")
            }, 500)


            //Glide.with(this).load(FirebaseAuth.getInstance().currentUser?.providerData?.get(0)?.photoUrl.toString()).into(contenedor_de_foto_perfil)
            //--------------------------------------------------------------------
        }


    }

    private fun updateRecycler(list: List<Senas>) {

            rv.apply {
                setHasFixedSize(true)
                adapter = SenasAdapter(list,{
                    vm.load(MainActivity.usery)
                    MainActivity.viewModelUser!!.load()
                }) {
                    //HomeFragment.wordViewModel?.callSena(it.palabra)
                    SenaActivity.sena = it
                    var index = list.indexOf(it)
                    SenaPageViewActivity.index = index
                    SenaPageViewActivity.senaList = list
                    val intent = Intent(context, SenaPageViewActivity::class.java)
                    startActivity(intent)
                }
                layoutManager = LinearLayoutManager(conext)
            }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.acercade, menu).apply {
            menu.findItem(R.id.acercade).setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId) {
                    com.DevRAT.lessa.R.id.acercade -> {
                        val acerca = LayoutInflater.from(context).inflate(R.layout.acercade, null)

                        val acerca_builder = AlertDialog.Builder(context!!)
                            .setView(acerca)

                        val acerca_alert = acerca_builder.show()

                        acerca.update_button.setOnClickListener {
                            acerca_alert.dismiss()
                        }
                        true
                    }

                    else -> false
                }
            }

            menu.findItem(R.id.actualizar_base).setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId) {
                    com.DevRAT.lessa.R.id.actualizar_base -> {
                        updateBase()
                        Snackbar.make(profile_container,"La base de datos a sido actualizada", Snackbar.LENGTH_LONG)
                            .setAction("Action", null)
                            .show()
                        true
                    }
                    else -> false

                }

            }
        }



    }


    fun change(position : Int){
        //Log.d("com.DevRAT.lessa","cahneg" + searchView!!.query.toString())
        //rv.adapter!!.notifyItemChanged(position)
        vm.load(MainActivity.usery)
        //vm.getSenaByNombre("%${searchView!!.query.toString()}%")
    }

    fun updateBase(){
        vmW = ViewModelProviders.of(conext as MainActivity).get(WordViewModel::class.java)
        val database = FirebaseDatabase.getInstance().reference.child(Statics.FIREBASE_TASK)
        val senaListener = object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                val mark = dataSnapshot.getValue(SenaFire::class.java)
                var sena = Senas(mark!!.palabra!!,mark!!.sena?.get(0)!!,mark!!.categoria!!,false)

                vmW.updateSena(sena)
                Log.d("aqui stoy", sena.toString())

            }
        }
        database!!.addChildEventListener(senaListener)
    }





    }