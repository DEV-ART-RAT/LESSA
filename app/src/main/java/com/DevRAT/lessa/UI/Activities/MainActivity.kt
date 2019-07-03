package com.DevRAT.lessa.UI.Activities

import android.content.Context
import android.graphics.ColorSpace
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Fragments.HomeFragment
import com.DevRAT.lessa.UI.Fragments.TestFragment
import com.DevRAT.lessa.UI.Fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.nav_view
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.DevRAT.lessa.BuildConfig
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.DevRAT.lessa.Database.ViewModel.SenaViewModel
import com.DevRAT.lessa.firebase.SenaFire
import com.DevRAT.lessa.firebase.Statics
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.recycler_view_list.*
import kotlinx.android.synthetic.main.recycler_view_list.view.*
import kotlinx.android.synthetic.main.recycler_view_list.view.tv_sena
import kotlinx.android.synthetic.main.welcome_layout.view.*



class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }
    private  var user: FirebaseUser? = null

    // Firebase instance variables
    //private var mFirebaseAuth: FirebaseAuth? = null
    //private var mFirebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        setContentView(com.DevRAT.lessa.R.layout.activity_main)
        setSupportActionBar(toolbarmain)
        user = FirebaseAuth.getInstance().currentUser
        viewModelUser= ViewModelProviders.of(this).get(SenaViewModel::class.java)
        viewModelUser!!.load()

        conext = this
        if(user?.email!=null){
            usery = user?.email.toString()
        }



        // Initialize Firebase Auth







        nav_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                com.DevRAT.lessa.R.id.action_home -> {
                    val fragment = HomeFragment.newInstance(this)
                    openFragmentInit(fragment)
                    true
                }
                com.DevRAT.lessa.R.id.action_test -> {
                    val fragment = TestFragment.newInstance(this)
                    openFragment(fragment)
                    true
                }
                com.DevRAT.lessa.R.id.action_profile -> {
                    //startActivity(Intent(this,GoogleSingInActivity::class.java))
                    val fragment = ProfileFragment.newInstance(account,this)
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        nav_view.selectedItemId = com.DevRAT.lessa.R.id.action_home


        when (getFirstTimeRun()) {
            0 -> welcome_menu()
            2 -> Snackbar.make(main_container,"Hemos actualizado, disfruta de los cambios", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.DevRAT.lessa.R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun openFragmentInit(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.DevRAT.lessa.R.id.main_container, fragment)
        transaction.commit()
    }

    private fun getFirstTimeRun(): Int {
        val sp = getSharedPreferences("MYAPP", 0)
        val result: Int
        val currentVersionCode = BuildConfig.VERSION_CODE
        val lastVersionCode = sp.getInt("FIRSTTIMERUN", -1)
        if (lastVersionCode == -1)
            result = 0
        else
            result = if (lastVersionCode == currentVersionCode) 1 else 2
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply()
        return result
    }

    fun welcome_menu() {
        val welcome = LayoutInflater.from(this).inflate(R.layout.welcome_layout, null)

        val welcome_builder =  AlertDialog.Builder(this)
            .setView(welcome)

        val welcome_alert = welcome_builder.show()

        welcome.welcome_button.setOnClickListener {
            welcome_alert.dismiss()
        }
    }

    companion object{
        var viewModelUser : SenaViewModel? = null
        var conext : Context? = null
        var usery : String = "default"

    }

    /*fun firebaseData() {


        val option = FirebaseRecyclerOptions.Builder<SenaFire>()
            .setQuery(database as Query, SenaFire::class.java)
            .setLifecycleOwner(this)
            .build()


        val firebaseRecyclerAdapter = object: FirebaseRecyclerAdapter<SenaFire, MyViewHolder>(option) {


            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(this@MainActivity).inflate(R.layout.recycler_view_list,parent,false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: SenaFire) {
                val placeid = getRef(position).key.toString()

                database!!.child(placeid).addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(this@MainActivity, "Error Occurred "+ p0.toException(), Toast.LENGTH_SHORT).show()

                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        //show_progress.visibility = if(itemCount == 0) View.VISIBLE else View.GONE
                        holder.tv_sena.text = model.palabra
                        holder.

                        holder.txt_name.setText(model.Name)
                        Picasso.get().load(model.Image).into(holder.img_vet)

                    }
                })
            }
        }
        mrecylerview.adapter = firebaseRecyclerAdapter
        firebaseRecyclerAdapter.startListening()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        internal var txt_name: TextView = itemView!!.findViewById<TextView>(R.id.Display_title)
        internal var img_vet: ImageView = itemView!!.findViewById<ImageView>(R.id.Display_img)


    }*/

}
