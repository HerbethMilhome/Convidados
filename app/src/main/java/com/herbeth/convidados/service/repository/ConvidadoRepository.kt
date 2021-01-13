package com.herbeth.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.herbeth.convidados.service.constants.DataBaseConstants
import com.herbeth.convidados.service.model.ConvidadoModel
import java.lang.Exception

class ConvidadoRepository private constructor(context: Context) {

    private var mConvidadoDataBaseHelper: ConvidadoDataBaseHelper = ConvidadoDataBaseHelper(context)

    /*
    Singleton
     */
    companion object {
        private lateinit var repositorio: ConvidadoRepository

        fun getInstance(context: Context): ConvidadoRepository {
            if (!::repositorio.isInitialized) {
                repositorio = ConvidadoRepository(context)
            }
            return repositorio
        }
    }

    fun salva(convidado: ConvidadoModel): Boolean {
        return try {
            val db = mConvidadoDataBaseHelper.writableDatabase

            val valores = ContentValues()
            valores.put(DataBaseConstants.CONVIDADO.COLUNAS.NOME, convidado.nome)
            valores.put(DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE, convidado.presenca)

            db.insert(DataBaseConstants.CONVIDADO.NOME_TABELA, null, valores)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun atualiza(convidado: ConvidadoModel): Boolean {
        return try {
            val db = mConvidadoDataBaseHelper.writableDatabase

            val valores = ContentValues()
            valores.put(DataBaseConstants.CONVIDADO.COLUNAS.NOME, convidado.nome)
            valores.put(DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE, convidado.presenca)

            val where = DataBaseConstants.CONVIDADO.COLUNAS.ID + " = ?"
            val parametro = arrayOf(convidado.id.toString())

            db.update(DataBaseConstants.CONVIDADO.NOME_TABELA, valores, where, parametro)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun exclui(id: Int): Boolean {
        return try {
            val db = mConvidadoDataBaseHelper.writableDatabase

            val where = DataBaseConstants.CONVIDADO.COLUNAS.ID + " = ?"
            val parametro = arrayOf(id.toString())

            db.delete(DataBaseConstants.CONVIDADO.NOME_TABELA, where, parametro)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun getConvidado(id: Int): ConvidadoModel? {

        var result: ConvidadoModel? = null

        return try {
            val db = mConvidadoDataBaseHelper.readableDatabase

            val where = DataBaseConstants.CONVIDADO.COLUNAS.ID + " = ?"
            val parameter = arrayOf(id.toString())
            val columns = arrayOf(DataBaseConstants.CONVIDADO.COLUNAS.ID, DataBaseConstants.CONVIDADO.COLUNAS.NOME, DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE)

            val cursor = db.query(DataBaseConstants.CONVIDADO.NOME_TABELA, columns, where, parameter, null, null, null)

            if (cursor != null && cursor.count > 0){
                cursor.moveToFirst()
                val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.NOME))
                val presenca = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE)) == 1)
                result = ConvidadoModel(id, nome, presenca)
            }

            cursor.close()

            result
        } catch (e: Exception) {
            result
        }
    }

    fun getTodos(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()

        return try {
            val db = mConvidadoDataBaseHelper.readableDatabase
            val columns = arrayOf(DataBaseConstants.CONVIDADO.COLUNAS.ID, DataBaseConstants.CONVIDADO.COLUNAS.NOME, DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE)
            val cursor = db.query(DataBaseConstants.CONVIDADO.NOME_TABELA, columns, null, null, null, null, null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.ID))
                    val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.NOME))
                    val presenca = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE)) == 1)

                    val convidado = ConvidadoModel(id, nome, presenca)
                    list.add(convidado)
                }
            }

            cursor.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getPresente(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return try {
            val db = mConvidadoDataBaseHelper.readableDatabase
            val cursor = db.rawQuery(getQueryPresentes(1),null
            )

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.ID))
                    val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.NOME))
                    val presenca = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE)) == 1)

                    val convidado = ConvidadoModel(id, nome, presenca)
                    list.add(convidado)
                }
            }

            cursor.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getAusente(): List<ConvidadoModel> {
        val list: MutableList<ConvidadoModel> = ArrayList()
        return try {
            val db = mConvidadoDataBaseHelper.readableDatabase
            val cursor = db.rawQuery(getQueryPresentes(0), null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.ID))
                    val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.NOME))
                    val presenca = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONVIDADO.COLUNAS.PRESENTE)) == 1)

                    val convidado = ConvidadoModel(id, nome, presenca)
                    list.add(convidado)
                }
            }

            cursor.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    private fun getQueryPresentes(presente: Int): String{
        return "select id, nome, presente from Convidados where presente = $presente"
    }
}