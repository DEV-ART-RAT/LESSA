package com.DevRAT.lessa.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Fragments.HomeFragment
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
            if (senas.favorito){
                like.setImageResource(R.drawable.likeon)
                like.setOnClickListener {
                    HomeFragment.wordViewModel?.updateSena(Senas(senas.palabra,senas.seña,senas.categoria,false))
                }
            }
            else
            {
                like.setImageResource(R.drawable.like)
                like.setOnClickListener {
                    HomeFragment.wordViewModel?.updateSena(Senas(senas.palabra,senas.seña,senas.categoria,true))
                }
            }
            setOnClickListener { clickListener(senas) }
        }
    }
}