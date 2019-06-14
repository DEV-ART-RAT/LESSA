package com.DevRAT.lessa.firebase

import com.google.firebase.auth.FirebaseAuth



class fireAuth(){
    private var mAuth: FirebaseAuth? = null

    init{
        mAuth = FirebaseAuth.getInstance();
    }



}

