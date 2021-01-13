package com.herbeth.convidados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AusenteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Ausente Fragment"
    }
    val text: LiveData<String> = _text
}