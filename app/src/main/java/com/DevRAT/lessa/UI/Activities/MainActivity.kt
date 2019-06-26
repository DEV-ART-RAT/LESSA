package com.DevRAT.lessa.UI.Activities

import android.net.Uri
import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Fragments.HomeFragment
import com.DevRAT.lessa.UI.Fragments.ListaFragment
import com.DevRAT.lessa.UI.Fragments.TestFragment
import com.DevRAT.lessa.UI.Fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.fragment_profile.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import android.R.id.edit
import android.content.SharedPreferences
import android.graphics.Color
import com.DevRAT.lessa.BuildConfig
//import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.welcome_layout.view.*


/*
SHA1: 71:18:89:C4:84:6A:DF:78:8A:31:B8:48:7D:32:B9:8C:86:53:0A:63
SHA-256: 89:2D:9B:78:B5:A4:49:11:D5:D6:97:0D:9B:FE:E4:54:EB:E5:52:5D:91:C0:56:5D:9E:78:39:F5:F2:AE:24:6B
 */


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
        user = FirebaseAuth.getInstance().currentUser

        // Initialize Firebase Auth


        nav_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                com.DevRAT.lessa.R.id.action_home -> {
                    val fragment = HomeFragment.newInstance(this)
                    openFragmentInit(fragment)
                    true
                }
                com.DevRAT.lessa.R.id.action_test -> {
                    val fragment = TestFragment.newInstance()
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
            1 -> Snackbar.make(main_container,"Bienvenido", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
            2 -> Toast.makeText(this, "Hemos actualizado, bienvenido de vuelta", Toast.LENGTH_SHORT).show()
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


}
