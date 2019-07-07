package com.DevRAT.lessa.firebase

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.ViewModel.WordViewModel
import com.DevRAT.lessa.UI.Activities.MainActivity
import com.DevRAT.lessa.UI.Fragments.ProfileFragment
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

object Statics {
    @JvmStatic val FIREBASE_TASK: String = "sena"

    fun updateBase(context: Context){
        val vmW = ViewModelProviders.of(context as FragmentActivity).get(WordViewModel::class.java)
        val database = FirebaseDatabase.getInstance().reference.child(Statics.FIREBASE_TASK)
        val senaListener = object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {
                val mark = dataSnapshot.getValue(SenaFire::class.java)
                var sena = Senas(mark!!.palabra!!,mark.sena?.get(0)!!,mark.categoria!!,false)
                vmW.updateSena(sena)
                Log.d("aqui stoy moved", sena.toString())
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {
                val mark = dataSnapshot.getValue(SenaFire::class.java)
                var sena = Senas(mark!!.palabra!!,mark!!.sena?.get(0)!!,mark!!.categoria!!,false)
                vmW.updateSena(sena)
                Log.d("aqui stoy change", sena.toString())
            }
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                //Log.d("actualizando",":v")
                val mark = dataSnapshot.getValue(SenaFire::class.java)
                var sena = Senas(mark!!.palabra!!,mark.sena?.get(0)!!,mark.categoria!!,false)
                vmW.updateSena(sena)
                //Log.d("aqui stoy", sena.toString())

            }
        }
        database.addChildEventListener(senaListener)
    }
}