package com.herbeth.convidados.service.constants

class ConvidadoConstants private constructor() {
    companion object {
        const val ID = "id"
    }

    object FILTRO {
        const val VAZIO = 0
        const val PRESENTE = 1
        const val AUSENTE = 2
    }
}