package com.herbeth.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.herbeth.convidados.viewmodel.ConvidadoFormViewModel
import com.herbeth.convidados.R
import kotlinx.android.synthetic.main.activity_form_convidados.*

class FormConvidadosActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: ConvidadoFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_convidados)

        mViewModel = ViewModelProvider(this).get(ConvidadoFormViewModel::class.java)
        setListeners()
        observa()
    }

    override fun onClick(v: View) {
        val id = v?.id
        if (id == R.id.button_salva) {
            val nome = edit_nome.text.toString()
            val presente = radio_pressente_sim.isChecked
            mViewModel.salvar(nome, presente)
        }
    }

    private fun setListeners() {
        button_salva.setOnClickListener(this)
    }

    private fun observa(){
        mViewModel.salvaConvidado.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Erro ao salvar", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }
}