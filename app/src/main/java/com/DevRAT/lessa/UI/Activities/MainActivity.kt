package com.DevRAT.lessa.UI.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Fragments.BooksFragment
import com.DevRAT.lessa.UI.Fragments.FilmsFragment
import com.DevRAT.lessa.UI.Fragments.MusicFragment
import kotlinx.android.synthetic.main.activity_main.nav_view

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_music -> {
                    val fragment = MusicFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.action_films -> {
                    val fragment = FilmsFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.action_books -> {
                    val fragment = BooksFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        nav_view.selectedItemId = R.id.action_music
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
