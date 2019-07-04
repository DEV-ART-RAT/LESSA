package com.DevRAT.lessa.UI.Adapter

import android.app.ProgressDialog
import android.content.Context
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
import androidx.core.os.HandlerCompat.postDelayed
import java.util.logging.Handler


class SenasAdapter(var senas: List<Senas>, private val clic : (Int)->Unit ,private val clickListener: (Senas) -> Unit) :
    RecyclerView.Adapter<SenasAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_list, parent, false)
        return ViewHolder(view,clic)
    }

    override fun getItemCount(): Int = senas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(senas[position], position,clickListener)


    class ViewHolder(item: View,val click :(Int)-> Unit) : RecyclerView.ViewHolder(item) {


        fun bind(senas: Senas, position: Int ,clickListener: (Senas) -> Unit) = with(itemView) {

            tv_sena.text = senas.palabra

            val vm = MainActivity.viewModelUser

            /*if (SenaViewModel.senass.value!!.any { it.palabra == senas.palabra }){
                Log.d("com.DevRAT.lessa", "estoy aqui" + senas.toString())
            }*/


            if (vm!!.senass.value!!.any { it.palabra == senas.palabra }) {
                like.setImageResource(R.drawable.button_likeon)
                like.setOnClickListener {
                    vm!!.delete(SenaUser(MainActivity.usery,senas.palabra))
                    //MainActivity.viewModelUser!!.load()
                    //Log.d("com.DevRAT.lessa","actualizando adpater")
                    //HomeFragment.wordViewModel?.updateSena(Senas(senas.palabra, senas.seña, senas.categoria, false))

                    val progressDialog = ProgressDialog(MainActivity.conext)
                    progressDialog.setTitle("Removiendo Favorito")
                    progressDialog.setMessage("Un momento")
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                    //progressDialog.incrementProgressBy(8)
                    progressDialog.show()
                    click(position)
                    val handler =  handler.postDelayed(Runnable {
                        //

                            progressDialog.hide()
                    }, 1000)

                    }
                    //bind(senas, clickListener)
                    //



            } else {
                like.setImageResource(R.drawable.button_like)
                like.setOnClickListener {
                    vm!!.insert(SenaUser(MainActivity.usery,senas.palabra))
                    //MainActivity.viewModelUser!!.load()
                    //HomeFragment.wordViewModel?.updateSena(Senas(senas.palabra, senas.seña, senas.categoria, true))


                    val progressDialog = ProgressDialog(MainActivity.conext)
                    progressDialog.setTitle("Agregando Favorito")
                    progressDialog.setMessage("Un momento")
                    progressDialog.setProgressStyle(ProgressDialog . STYLE_SPINNER)
                    //progressDialog.incrementProgressBy(8)
                    progressDialog.show()
                    click(position)
                    val handler =  handler.postDelayed(Runnable {
                        //click(position)
                            progressDialog.hide()
                    }, 1000)



                }
            }
            setOnClickListener { clickListener(senas) }
        }


    }
}