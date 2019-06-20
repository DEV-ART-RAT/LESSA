package com.DevRAT.lessa.UI.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.DevRAT.lessa.R
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_google_sing_in.*
import android.R.attr.data
import android.app.ProgressDialog
import android.net.Uri
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Task




/**
 * Demonstrate Firebase Authentication using a Google ID Token.
 */
class GoogleSingInActivity : AppCompatActivity(), View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    private var account: GoogleSignInAccount? = null
    // [END declare_auth]

    private lateinit var googleSignInClient: GoogleSignInClient
    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_google_sing_in)

        // Button listeners
        //signInButton.setOnClickListener(this)
        //signOutButton.setOnClickListener(this)
        //disconnectButton.setOnClickListener(this)

        // [START config_signin]
        // Configure Google Sign In
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("shoto mate")
        progressDialog.show()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()

            .build()
        // [END config_signin]
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        //Log.d("gso" , gso.toString())
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // [END initialize_auth]

        //intent.getIntExtra("CODIGO")

        if(intent.extras["CODIGO"] == 1){
            signIn()
        } else{
            signOut()
        }

    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        account = GoogleSignIn.getLastSignedInAccount(this)
        //updateUI(currentUser)
    }
    // [END on_start_check_user]

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            /*val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                var accounte = task.getResult(ApiException::class.java)
                // Signed in successfully, show authenticated UI.
                Toast.makeText(applicationContext, "SignIn: susses init!" + accounte?.email,
                    Toast.LENGTH_LONG).show()

    } catch (e: ApiException) {
        // The ApiException status code indicates the detailed failure reason.
        // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Toast.makeText(applicationContext, "SignIn: fail init! " + e,
                    Toast.LENGTH_LONG).show()
    }*/
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                // successful -> authenticate with Firebase
                val account = result.signInAccount
                setResult(1 )
                firebaseAuthWithGoogle(account!!)
                //Toast.makeText(applicationContext, "SignIn: susses ;v    !" + result.status,
                   // Toast.LENGTH_LONG).show()
            } else {
                // failed -> update UI
                //updateUI(null)
                Toast.makeText(applicationContext, "SignIn: failed init!" + result.status + "finshi result",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {

        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        // [START_EXCLUDE silent]
        //showProgressDialog()
        // [END_EXCLUDE]

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(applicationContext, "secion ya iniciada",
                        Toast.LENGTH_LONG).show()
                    Log.d(TAG, "signInWithCredential:success " + auth.currentUser.toString())
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    //Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                // [START_EXCLUDE]
                //hideProgressDialog()
                // [END_EXCLUDE]
            }

        finish()
    }
    // [END auth_with_google]

    // [START signin]
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        //startActivityForResult(signInIntent, RC_SIGN_IN)

        //val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signin]

    private fun signOut() {
        // Firebase sign out
        auth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            //updateUI(null)

            setResult(2)
            finish()
        }
    }

    /*private fun revokeAccess() {
        // Firebase sign out
        signOutButton.text = account?.email.toString()
        Toast.makeText(applicationContext, "current user" + auth.currentUser.toString()+auth.currentUser?.providerData?.get(0)?.photoUrl.toString(),
            Toast.LENGTH_LONG).show()
        /*auth.signOut()

        // Google revoke access
        googleSignInClient.revokeAccess().addOnCompleteListener(this) {
            updateUI(null)
        }*/
    }*/

    /*
    private fun updateUI(user: FirebaseUser?) {
        //Log.d(TAG, "signInWithCredential:success")
        signOutButton.text = auth.currentUser?.email.toString()
        Glide.with(this).load(auth.currentUser?.providerData?.get(0)?.photoUrl.toString()).into(my_perfil_icon)
        //my_perfil_icon.setImageURI(Uri.parse("https://lh3.googleusercontent.com/-nNDxFpt8BkY/XQq1zjBcjXI/AAAAAAAAAww/m2J9GjkbNqQ0kghSNbSaDnSwcgBbUmVPwCEwYBhgL/w139-h140-p/457616_1920_1080.jpg"/*auth.currentUser?.providerData?.get(0)?.photoUrl.toString()*/ ))

    }*/




    /*{
        hideProgressDialog()
        if (user != null) {
            status.text = getString(R.string.google_status_fmt, user.email)
            detail.text = getString(R.string.firebase_status_fmt, user.uid)

            signInButton.visibility = View.GONE
            signOutAndDisconnect.visibility = View.VISIBLE
        } else {
            status.setText(R.string.signed_out)
            detail.text = null

            signInButton.visibility = View.VISIBLE
            signOutAndDisconnect.visibility = View.GONE
        }
    }*/

    override fun onClick(v: View) {
        /*val i = v.id
        when (i) {
            R.id.signInButton -> signIn()
            R.id.signOutButton -> signOut()
            R.id.disconnectButton -> signOut()
        }*/
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 1234
    }
}