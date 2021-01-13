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
import com.herbeth.convidados.viewmodel.PresenteViewModel

class PresenteFragment : Fragment() {

    private lateinit var presenteViewModel: PresenteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        presenteViewModel = ViewModelProvider(this).get(PresenteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_presente, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        presenteViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}