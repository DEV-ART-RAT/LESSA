package com.DevRAT.lessa.UI.Activities

import android.net.Uri
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Fragments.HomeFragment
import com.DevRAT.lessa.UI.Fragments.TestFragment
import com.DevRAT.lessa.UI.Fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.fragment_profile.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount







class MainActivity : AppCompatActivity(), HomeFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }
    private  var user: FirebaseUser? = null

    // Firebase instance variables
    private var mFirebaseAuth: FirebaseAuth? = null
    private var mFirebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbarmain)
        user = FirebaseAuth.getInstance().currentUser

        // Initialize Firebase Auth


        nav_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.action_test -> {
                    val fragment = TestFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.action_profile -> {
                    //startActivity(Intent(this,GoogleSingInActivity::class.java))
                    val fragment = ProfileFragment.newInstance(account,this)
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        nav_view.selectedItemId = R.id.action_home
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
