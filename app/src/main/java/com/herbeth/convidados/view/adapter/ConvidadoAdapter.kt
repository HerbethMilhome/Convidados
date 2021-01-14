package com.herbeth.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.herbeth.convidados.R
import com.herbeth.convidados.service.model.ConvidadoModel
import com.herbeth.convidados.view.listener.ConvidadoListener
import com.herbeth.convidados.view.viewholder.ConvidadoViewHolder

class ConvidadoAdapter : RecyclerView.Adapter<ConvidadoViewHolder>() {

    private var mConvidadosList: List<ConvidadoModel> = arrayListOf()
    private lateinit var mListener: ConvidadoListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvidadoViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_convidado, parent, false)
        return ConvidadoViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: ConvidadoViewHolder, position: Int) {
        holder.bind(mConvidadosList[position])
    }

    override fun getItemCount(): Int {
        return mConvidadosList.count()
    }

    fun updateConvidados(list: List<ConvidadoModel>) {
        mConvidadosList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: ConvidadoListener) {
        mListener = listener
    }

}