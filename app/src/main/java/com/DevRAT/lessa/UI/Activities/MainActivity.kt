package com.DevRAT.lessa.UI.Activities

import android.content.Context
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
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.ViewModel.SenaViewModel
import com.DevRAT.lessa.Database.ViewModel.WordViewModel
import com.DevRAT.lessa.firebase.SenaFire
import com.DevRAT.lessa.firebase.Statics
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.welcome_layout.view.*



class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener {

    private lateinit var vmW: WordViewModel

    override fun onFragmentInteraction(uri: Uri) {

    }
    private  var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarmain)
        //windowManager.defaultDisplay.getMetrics(displayMetrics)
        user = FirebaseAuth.getInstance().currentUser
        viewModelUser= ViewModelProviders.of(this).get(SenaViewModel::class.java)
        viewModelUser!!.load()

        conext = this

        if(user?.email!=null){
            usery = user?.email.toString()
        }

        nav_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment.newInstance(this)
                    openFragmentInit(fragment)
                    true
                }
                R.id.action_test -> {
                    val fragment = TestFragment.newInstance(this)
                    openFragment(fragment)
                    true
                }
                R.id.action_profile -> {
                    val fragment = ProfileFragment.newInstance(account,this)
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        nav_view.selectedItemId = R.id.action_home


        when (getFirstTimeRun()) {
            0 -> welcome_menu()
            2 -> update_menu()
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun openFragmentInit(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
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

        welcome_alert.window.setBackgroundDrawableResource(R.drawable.dialog_bg)


        welcome.update_button.setOnClickListener {
            welcome_alert.dismiss()
            update_menu()
        }
    }

    fun update_menu() {

        Statics.updateBase(this)
        val welcome = LayoutInflater.from(this).inflate(R.layout.update_layout, null)

        val welcome_builder =  AlertDialog.Builder(this)
            .setView(welcome)

        val welcome_alert = welcome_builder.show()

        welcome_alert.window.setBackgroundDrawableResource(R.drawable.dialog_bg)


        welcome.update_button.setOnClickListener {
            welcome_alert.dismiss()
        }
    }

    companion object{
        var viewModelUser : SenaViewModel? = null
        var conext : Context? = null
        var usery : String = "default"
        //val displayMetrics = DisplayMetrics()

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
