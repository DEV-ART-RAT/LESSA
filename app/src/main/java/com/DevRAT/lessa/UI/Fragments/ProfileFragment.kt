package com.DevRAT.lessa.UI.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Activities.GoogleSingInActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_google_sing_in.*
import kotlinx.android.synthetic.main.fragment_profile.*
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
}