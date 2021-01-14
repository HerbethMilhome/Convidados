package com.herbeth.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.herbeth.convidados.viewmodel.ConvidadoFormViewModel
import com.herbeth.convidados.R
import com.herbeth.convidados.service.constants.ConvidadoConstants
import kotlinx.android.synthetic.main.activity_form_convidados.*

class ConvidadosFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: ConvidadoFormViewModel
    private var mConvidadoId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_convidados)

        mViewModel = ViewModelProvider(this).get(ConvidadoFormViewModel::class.java)

        setListeners()
        observa()
        carregaDados()

        radio_pressente_sim.isChecked = true
    }

    override fun onClick(v: View) {
        val id = v?.id
        if (id == R.id.button_salva) {
            val nome = edit_nome.text.toString()
            val presente = radio_pressente_sim.isChecked

            mViewModel.salvar(mConvidadoId, nome, presente)

        }
    }

    private fun carregaDados() {
        val bundle = intent.extras
        if (bundle != null) {
            mConvidadoId = bundle.getInt(ConvidadoConstants.ID)
            mViewModel.carregarById(mConvidadoId)
        }
    }

    private fun setListeners() {
        button_salva.setOnClickListener(this)
    }

    private fun observa() {
        mViewModel.salvaConvidado.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Erro ao salvar", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.convidado.observe(this, Observer {
            edit_nome.setText(it.nome)
            if (it.presenca) {
                radio_pressente_sim.isChecked = true
            } else {
                radio_pressente_nao.isChecked = true
            }
        })
    }
}