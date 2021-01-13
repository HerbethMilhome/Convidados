package com.herbeth.convidados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PresenteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Presente Fragment"
    }
    val text: LiveData<String> = _text
}