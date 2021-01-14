package com.herbeth.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.herbeth.convidados.service.model.ConvidadoModel
import com.herbeth.convidados.service.repository.ConvidadoRepository

class TodosViewModel(application: Application) : AndroidViewModel(application) {

    private val mConvidadoRepository = ConvidadoRepository.getInstance(application.applicationContext)
    private val mConvidadoList = MutableLiveData<List<ConvidadoModel>>()
    val convidadoList: LiveData<List<ConvidadoModel>> = mConvidadoList

    fun listaTodos() {
        mConvidadoList.value = mConvidadoRepository.getTodos()
    }

    fun delete(id: Int) {
        mConvidadoRepository.excluir(id)
    }
}