package com.herbeth.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herbeth.convidados.service.constants.ConvidadoConstants
import com.herbeth.convidados.service.model.ConvidadoModel
import com.herbeth.convidados.service.repository.ConvidadoRepository

class TodosViewModel(application: Application) : AndroidViewModel(application) {

    private val mConvidadoRepository = ConvidadoRepository(application.applicationContext)
    private val mConvidadoList = MutableLiveData<List<ConvidadoModel>>()
    val convidadoList: LiveData<List<ConvidadoModel>> = mConvidadoList

    fun lista(filtro: Int) {
        if (filtro == ConvidadoConstants.FILTRO.VAZIO) {
            mConvidadoList.value = mConvidadoRepository.getTodos()
        } else if (filtro == ConvidadoConstants.FILTRO.PRESENTE) {
            mConvidadoList.value = mConvidadoRepository.getPresente()
        } else {
            mConvidadoList.value = mConvidadoRepository.getAusente()
        }
    }

    fun delete(id: Int) {
        var conv = mConvidadoRepository.getConvidado(id)
        if (conv != null) {
            mConvidadoRepository.excluir(conv)
        }
    }
}