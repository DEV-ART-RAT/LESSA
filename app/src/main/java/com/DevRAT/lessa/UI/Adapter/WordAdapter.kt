package com.example.myapplication.Adapter

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build.ID
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.DevRAT.lessa.Database.Entities.Word
import com.DevRAT.lessa.R


abstract class WordAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    abstract fun addListener(holder: WordViewHolder, palabra: String, Categoria: String, seña: Int, favorito: Boolean, Status: Boolean)

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_word: TextView = itemView.findViewById(R.id.tv_word)
        val img_categoria: ImageView = itemView.findViewById(R.id.img_categoria)
        val word_container:LinearLayout = itemView.findViewById(R.id.item_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recycle_view_item, parent, false)
        return WordViewHolder(itemView)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.tv_word.text = current.palabra
        holder.img_categoria.setImageResource(current.seña)
        addListener(holder,current.palabra, current.categoria, current.seña, current.favorito, current.status)

    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }


    override fun getItemCount() = words.size


}


