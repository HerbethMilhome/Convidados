package com.herbeth.convidados.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.herbeth.convidados.service.model.ConvidadoModel

@Database(entities = [ConvidadoModel::class], version = 1)
abstract class ConvidadoDataBase() : RoomDatabase() {

    abstract fun convidadoDAO(): ConvidadoDAO

    companion object {
        private lateinit var INSTANCE: ConvidadoDataBase

        fun getDataBase(context: Context): ConvidadoDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(ConvidadoDataBase::class) {
                    INSTANCE =
                        Room.databaseBuilder(context, ConvidadoDataBase::class.java, "ConvidadoDb")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

    }

}