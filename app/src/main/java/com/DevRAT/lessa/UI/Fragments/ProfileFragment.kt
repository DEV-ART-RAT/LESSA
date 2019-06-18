package com.DevRAT.lessa.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {
    var auth: GoogleSignInAccount? = null

    companion object {
        fun newInstance(auth: GoogleSignInAccount?): ProfileFragment = ProfileFragment().apply {
            this.auth = auth
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false).apply {
           actividad_session_lanzar.text = auth?.email.toString()
        }
    }
}