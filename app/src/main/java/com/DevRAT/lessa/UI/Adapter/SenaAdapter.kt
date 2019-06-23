package com.example.myapplication.Adapter


import android.annotation.SuppressLint
import android.content.Context
import android.os.Build.ID
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.Entities.Word
import com.DevRAT.lessa.R


abstract class SenaAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SenaAdapter.WordViewHolder>() {

    abstract fun addListener(holder: WordViewHolder, palabra: String)

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var senas = emptyList<Senas>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_sena: TextView = itemView.findViewById(R.id.tv_sena)
        val sena_container:LinearLayout = itemView.findViewById(R.id.ly_sena_container)
        val likeButton: ImageButton = itemView.findViewById(R.id.like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_view_list, parent, false)
        return WordViewHolder(itemView)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = senas[position]
        holder.tv_sena.text = current.palabra
        if (current.favorito){
            holder.likeButton.setImageResource(R.drawable.likeon)
            holder.likeButton.setOnClickListener(View.OnClickListener {
                holder.likeButton.setImageResource(R.drawable.like)
            })
        } else {
            holder.likeButton.setOnClickListener(View.OnClickListener {
                holder.likeButton.setImageResource(R.drawable.likeon)
            })
        }

        addListener(holder,current.palabra)

    }

    internal fun setSenas(senas: List<Senas>) {
        this.senas = senas
        notifyDataSetChanged()
    }


    override fun getItemCount() = senas.size


}


