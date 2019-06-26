package com.DevRAT.lessa.UI.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.ViewModel.SenasViewModel
import com.DevRAT.lessa.Database.ViewModel.WordViewModel
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Adapter.SenaAdapter
import com.DevRAT.lessa.UI.Adapter.SenasAdapter
import kotlinx.android.synthetic.main.fragment_lista.*
import kotlinx.android.synthetic.main.fragment_lista.view.*
import kotlinx.android.synthetic.main.fragment_profile.*

class favoritosList : Fragment(){


    private var listener: OnFragmentInteractionListener? = null
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        var view =  inflater.inflate(R.layout.fragment_lista, container, false)
        initAll(view)
        return view
    }



    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener") as Throwable
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ListaFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    fun initAll(view: View) {


        val recyclerView = view.recyclerviewList
        val adapter = object : SenaAdapter(view.context) {
            override fun addListener(
                holder: WordViewHolder,
                palabra: String) {
                holder.sena_container.setOnClickListener {

                }
            }

        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)


        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        wordViewModel.callFavoritos()

        WordViewModel.allfavoritos?.observe(this, Observer { senas ->
            senas.let { adapter.setSenas(it)
                //recyclerView.adapter.notifyDataSetChanged()
            }

        })

    }

}