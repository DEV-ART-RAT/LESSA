package com.DevRAT.lessa.UI.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Activities.GoogleSingInActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_google_sing_in.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {
    var auth: GoogleSignInAccount? = null
     var  conext :Context? =null

    companion object {
        fun newInstance(auth: GoogleSignInAccount?,context : Context): ProfileFragment = ProfileFragment().apply {
            this.auth = auth
            this.conext = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false).apply {
           actividad_session_lanzar.text = FirebaseAuth.getInstance().currentUser?.email.toString()//auth?.email.toString()

            Glide.with(this).load(FirebaseAuth.getInstance().currentUser?.providerData?.get(0)?.photoUrl.toString()).into(contenedor_de_foto_perfil)

            actividad_session_lanzar.setOnClickListener {
                startActivity(Intent(conext, GoogleSingInActivity::class.java))

            }
        }
    }
}