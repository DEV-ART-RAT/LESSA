package com.DevRAT.lessa.UI.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.DevRAT.lessa.Database.Entities.Senas
import com.DevRAT.lessa.Database.ViewModel.WordViewModel
import com.DevRAT.lessa.R
import com.DevRAT.lessa.UI.Activities.MainActivity
import com.DevRAT.lessa.UI.Activities.SenaActivity
import com.DevRAT.lessa.UI.Adapter.SenasAdapter
import kotlinx.android.synthetic.main.fragment_test.view.*


class TestFragment : Fragment() {

    var  conext : Context? =null
    lateinit var categoria: String

    private lateinit var vm: WordViewModel
    private lateinit var rv: RecyclerView
    private lateinit var senasAdapter: SenasAdapter
    private lateinit var observer : Observer<List<Senas>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return  inflater.inflate(R.layout.fragment_test, container, false).apply{
            vm = ViewModelProviders.of(conext as MainActivity).get(WordViewModel::class.java)
            rv = rv_busqueda
            observer = Observer<List<Senas>> {
                updateRecycler(it)
            }
//            val h=""
//            vm.getSenaByNombre("%$h%")
            vm.busca.observe(conext as LifecycleOwner, observer)
            //return view
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(context: Context) =
            TestFragment().apply {
                this.conext = context
                arguments = Bundle().apply {

                }
            }
    }



    private fun updateRecycler(list: List<Senas>) {

        rv.apply {
            setHasFixedSize(true)
            adapter = SenasAdapter(list) {
                //wordViewModel?.callSena(it.palabra)
                SenaActivity.sena = it
                val intent : Intent = Intent(context, SenaActivity::class.java)
                startActivity(intent)
            }
            layoutManager = LinearLayoutManager(conext)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Hace que cuando presiones el botón de sumbit se ejecute lo que pongas aquí
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Hace que cambie dinamicamente mientras escribis, porque ejecuta lo que pongas aquí cada vez que escribis.
                Log.e("com.DevRAT,lessa",newText?:"no hay ma")
               //queryToDatabase(newText?: "N/A")

                vm.getSenaByNombre("%$newText%")
                vm.busca.observe(conext as LifecycleOwner, observer)
                return true
            }

        })

    }

}