package com.DevRAT.lessa.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.DevRAT.lessa.R



class BooksFragment : Fragment() {
    companion object {
        fun newInstance(): BooksFragment = BooksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_books, container, false)
}