package com.herbeth.convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.herbeth.convidados.service.constants.DataBaseConstants

class ConvidadoDataBaseHelper(contexto: Context) : SQLiteOpenHelper(contexto, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_CONVIDADOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_VERSION = 1 //sempre q tiver alteracao no banco tem q atualizar a versao do banco
        private const val DATABASE_NAME = "Convidado.db"

        private const val CREATE_TABLE_CONVIDADOS =
                ("create table " + DataBaseConstants.CONVIDADO.NOME_TABELA + "("
                        + DataBaseConstants.CONVIDADO.COLUNAS.ID + " integer primary key autoincrement, "
                        + DataBaseConstants.CONVIDADO.COLUNAS.NOME + " text, "
                        + DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE + " integer); ")
    }

}