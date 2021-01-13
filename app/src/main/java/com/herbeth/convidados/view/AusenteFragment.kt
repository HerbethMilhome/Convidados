package com.herbeth.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.herbeth.convidados.R
import com.herbeth.convidados.viewmodel.AusenteViewModel

class AusenteFragment : Fragment() {

    private lateinit var ausenteViewModel: AusenteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        ausenteViewModel =
                ViewModelProvider(this).get(AusenteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ausente, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        ausenteViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}