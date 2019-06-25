package com.DevRAT.lessa.UI.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.ViewModel.SenasViewModel
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Activities.GoogleSingInActivity
import com.DevRAT.lessa.UI.Activities.MainActivity
import com.DevRAT.lessa.UI.Adapter.SenasAdapter
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_google_sing_in.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {


    private lateinit var vm: SenasViewModel
    private lateinit var rv: RecyclerView
    private lateinit var observer : Observer<List<Senas>>

    companion object {
        var auth: GoogleSignInAccount? = null
        var  conext :Context? =null
        fun newInstance(auth1: GoogleSignInAccount?,context : Context): ProfileFragment = ProfileFragment().apply {
            auth = auth1
            conext = context


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false).apply {

            vm = ViewModelProviders.of(conext as MainActivity).get(SenasViewModel::class.java)
            rv = recycle_view_profile
            vm.load()
            observer = Observer<List<Senas>> {
                updateRecycler(it)
            }
            SenasViewModel.senass?.observe(conext as LifecycleOwner, observer)


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
            actividad_session_lanzar.visibility =View.GONE
            boton_salir_google.visibility =View.VISIBLE
            actividad_session_lanzar2.visibility = View.VISIBLE
            Glide.with(this).load(account?.photoUrl.toString()).into(contenedor_de_foto_perfil)
            contenedor_de_foto_perfil.visibility = View.VISIBLE
            //--------------------------------------------------------------------
        }
        else{
            //--------------si algo falla al iniciar session----------------------
            actividad_session_lanzar.visibility =View.VISIBLE//auth?.email.toString()
            boton_salir_google.visibility =View.GONE
            actividad_session_lanzar2.visibility = View.GONE
            contenedor_de_foto_perfil.visibility = View.GONE
            //Glide.with(this).load(FirebaseAuth.getInstance().currentUser?.providerData?.get(0)?.photoUrl.toString()).into(contenedor_de_foto_perfil)
            //--------------------------------------------------------------------
        }


    }

    private fun updateRecycler(list: List<Senas>) {
        if (rv.adapter == null) {
            rv.apply {
                setHasFixedSize(true)
                adapter = SenasAdapter(list) {
                    Log.d("com.DevRAT.lessa", "Toco $it")
                }
                layoutManager = LinearLayoutManager(conext)
            }
        } else {
            val adapter = rv.adapter as SenasAdapter
            adapter.senas = list
            adapter.notifyDataSetChanged()
        }
    }
}