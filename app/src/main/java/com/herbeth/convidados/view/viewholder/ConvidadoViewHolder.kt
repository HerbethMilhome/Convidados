package com.herbeth.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.herbeth.convidados.R
import com.herbeth.convidados.service.model.ConvidadoModel
import com.herbeth.convidados.view.listener.ConvidadoListener

class ConvidadoViewHolder(itemView: View, private val listener: ConvidadoListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(convidado: ConvidadoModel){
        val textItemConvidado = itemView.findViewById<TextView>(R.id.text_view_linha_convidado)
        textItemConvidado.text = convidado.nome

        textItemConvidado.setOnClickListener {
            listener.onClick(convidado.id)
        }

        textItemConvidado.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                    .setTitle(R.string.remocao_convidado)
                    .setMessage(R.string.deseja_remover)
                    .setPositiveButton(R.string.sim) { dialog, which ->
                        listener.onDelete(convidado.id)
                    }
                    .setNeutralButton(R.string.nao, null)
                    .show()

            true
        }
    }

}