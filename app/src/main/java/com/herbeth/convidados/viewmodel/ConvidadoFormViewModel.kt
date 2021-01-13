package com.herbeth.convidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herbeth.convidados.service.model.ConvidadoModel
import com.herbeth.convidados.service.repository.ConvidadoRepository

class ConvidadoFormViewModel(application: Application): AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mConvidadoRepository : ConvidadoRepository = ConvidadoRepository.getInstance(mContext)
    private var mSalvaConvidado = MutableLiveData<Boolean>()

    val salvaConvidado: LiveData<Boolean> = mSalvaConvidado

    fun salvar(nome: String, presenca: Boolean) {
        val convidado = ConvidadoModel(nome = nome, presenca = presenca)
        mSalvaConvidado.value = mConvidadoRepository.salva(convidado)
    }

}