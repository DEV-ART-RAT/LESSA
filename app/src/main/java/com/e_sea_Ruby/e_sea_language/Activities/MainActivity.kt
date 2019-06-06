package com.e_sea_Ruby.e_sea_language.Activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import com.e_sea_Ruby.e_sea_language.*
import com.e_sea_Ruby.e_sea_language.Fragments.Home_Fragment
import com.e_sea_Ruby.e_sea_language.Fragments.Profile_Fragment
import com.e_sea_Ruby.e_sea_language.Fragments.Search_Fragment
import com.e_sea_Ruby.e_sea_language.Login.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_menu_.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //FRAGMENTO INICIO
        if (savedInstanceState == null) {
            var fragment: Home_Fragment = Home_Fragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.FL_manager, fragment).commit()
        }

        btn_fr_1.setOnClickListener {
            var fragment: Search_Fragment = Search_Fragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.FL_manager, fragment).commit()
        }

        btn_fr_2.setOnClickListener {
            var fragment: Home_Fragment = Home_Fragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.FL_manager, fragment).commit()
        }
        btn_fr_3.setOnClickListener {
            var fragment: Profile_Fragment = Profile_Fragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.FL_manager, fragment).commit()
        }

        //

//
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
                startActivity(Intent(this, LoginActivity::class.java))

            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}
