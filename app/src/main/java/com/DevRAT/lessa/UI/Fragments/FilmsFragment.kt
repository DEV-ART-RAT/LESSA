package com.DevRAT.lessa.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R



class FilmsFragment : Fragment() {
    companion object {
        fun newInstance(): FilmsFragment = FilmsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_films, container, false)
}