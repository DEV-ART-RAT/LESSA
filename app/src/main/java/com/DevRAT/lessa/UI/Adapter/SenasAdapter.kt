package com.DevRAT.lessa.UI.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.DevRAT.lessa.Database.DAO.SenaUserDao
import com.DevRAT.lessa.Database.Entities.SenaUser
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.ViewModel.SenaViewModel
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Activities.MainActivity
import com.DevRAT.lessa.UI.Fragments.HomeFragment
import com.DevRAT.lessa.firebase.SenaFire
import com.DevRAT.lessa.firebase.Statics
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.recycler_view_list.view.*

class SenasAdapter(var senas: List<Senas>, private val clickListener: (Senas) -> Unit) :
    RecyclerView.Adapter<SenasAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = senas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(senas[position], clickListener)


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {


        fun bind(senas: Senas, clickListener: (Senas) -> Unit) = with(itemView) {

            tv_sena.text = senas.palabra


            val vm = MainActivity.viewModelUser

            /*if (SenaViewModel.senass.value!!.any { it.palabra == senas.palabra }){
                Log.d("com.DevRAT.lessa", "estoy aqui" + senas.toString())
            }*/


            if (senas.favorito || SenaViewModel.senass.value!!.any { it.palabra == senas.palabra }) {
                like.setImageResource(R.drawable.button_likeon)
                like.setOnClickListener {
                    vm!!.delete(SenaUser("sho",senas.palabra))
                    MainActivity.viewModelUser!!.load()
                    HomeFragment.wordViewModel?.updateSena(Senas(senas.palabra, senas.seña, senas.categoria, false))

                }
            } else {
                like.setOnClickListener {
                    vm!!.insert(SenaUser("sho",senas.palabra))
                    MainActivity.viewModelUser!!.load()
                    HomeFragment.wordViewModel?.updateSena(Senas(senas.palabra, senas.seña, senas.categoria, true))
                }
            }
            setOnClickListener { clickListener(senas) }
        }


    }
}