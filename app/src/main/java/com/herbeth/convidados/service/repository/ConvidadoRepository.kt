package com.herbeth.convidados.service.repository

import android.content.Context
import com.herbeth.convidados.service.model.ConvidadoModel
import kotlinx.coroutines.InternalCoroutinesApi

class ConvidadoRepository (context: Context) {

    private val mDataBase = ConvidadoDataBase.getDataBase(context).convidadoDAO()

    fun salvar(convidado: ConvidadoModel): Boolean {
        return mDataBase.salvar(convidado) > 0
    }

    fun atualizar(convidado: ConvidadoModel): Boolean {
        return mDataBase.atualizar(convidado) > 0
    }

    fun excluir(convidado: ConvidadoModel) {
        return mDataBase.excluir(convidado)
    }

    fun getConvidado(id: Int): ConvidadoModel? {
        return mDataBase.getConvidadoById(id)
    }

    fun getTodos(): List<ConvidadoModel> {
        return mDataBase.getTodos()
    }

    fun getPresente(): List<ConvidadoModel> {
        return mDataBase.getPresentes()
    }

    fun getAusente(): List<ConvidadoModel> {
        return mDataBase.getAusentes()
    }

}