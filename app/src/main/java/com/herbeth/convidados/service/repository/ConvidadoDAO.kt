package com.herbeth.convidados.service.repository

import androidx.room.*
import com.herbeth.convidados.service.model.ConvidadoModel

@Dao
interface ConvidadoDAO {

    @Insert
    fun salvar(convidado: ConvidadoModel) : Long

    @Update
    fun atualizar(convidado: ConvidadoModel) : Int

    @Delete
    fun excluir(convidado: ConvidadoModel)

    @Query("SELECT * FROM Convidado WHERE id = :id")
    fun getConvidadoById(id: Int): ConvidadoModel

    @Query("SELECT * FROM Convidado")
    fun getTodos(): List<ConvidadoModel>

    @Query("SELECT * FROM Convidado WHERE presenca = 1")
    fun getPresentes(): List<ConvidadoModel>

    @Query("SELECT * FROM Convidado WHERE presenca = 0")
    fun getAusentes(): List<ConvidadoModel>
}