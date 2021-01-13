package com.herbeth.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.herbeth.convidados.R
import com.herbeth.convidados.view.adapter.ConvidadoAdapter
import com.herbeth.convidados.viewmodel.TodosViewModel

class TodosFragment : Fragment() {

    private lateinit var todosViewModel: TodosViewModel
    private val mAdapter: ConvidadoAdapter = ConvidadoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        todosViewModel = ViewModelProvider(this).get(TodosViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_todos, container, false)

        // pega a recycler
        val recycle = root.findViewById<RecyclerView>(R.id.recycle_todos_convidados)
        //define layout
        recycle.layoutManager = LinearLayoutManager(context)
        //define adapter
        recycle.adapter = mAdapter

        observer()

        todosViewModel.listaTodos()

        return root
    }

    private fun observer(){
        todosViewModel.convidadoList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateConvidados(it) //it é a lista
        })
    }
}