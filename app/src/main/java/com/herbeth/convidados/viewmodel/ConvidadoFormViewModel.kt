package com.herbeth.convidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herbeth.convidados.service.model.ConvidadoModel
import com.herbeth.convidados.service.repository.ConvidadoRepository

class ConvidadoFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mConvidadoRepository: ConvidadoRepository = ConvidadoRepository(mContext)
    private var mSalvaConvidado = MutableLiveData<Boolean>()
    private var mConvidado = MutableLiveData<ConvidadoModel>()

    val salvaConvidado: LiveData<Boolean> = mSalvaConvidado
    val convidado: LiveData<ConvidadoModel> = mConvidado

    fun salvar(id: Int, nome: String, presenca: Boolean) {
        val convidado = ConvidadoModel().apply {
            this.id = id
            this.nome = nome
            this.presenca = presenca
        }

        if (id == 0){
            mSalvaConvidado.value = mConvidadoRepository.salvar(convidado)
        } else {
            mSalvaConvidado.value = mConvidadoRepository.atualizar(convidado)
        }

    }

    fun carregarById(id: Int) {
        mConvidado.value = mConvidadoRepository.getConvidado(id)
    }
}