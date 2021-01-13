package com.herbeth.convidados.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.herbeth.convidados.R
import com.herbeth.convidados.service.model.ConvidadoModel

class ConvidadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(convidado: ConvidadoModel){
        val textItemConvidado = itemView.findViewById<TextView>(R.id.text_view_linha_convidado)
        textItemConvidado.text = convidado.nome
    }

}