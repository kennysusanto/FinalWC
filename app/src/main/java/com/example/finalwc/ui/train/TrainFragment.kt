package com.example.finalwc.ui.train

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.finalwc.R

class TrainFragment : Fragment() {

    private lateinit var trainViewModel: TrainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trainViewModel =
            ViewModelProviders.of(this).get(trainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_train, container, false)
        val textView: TextView = root.findViewById(R.id.text_train)
        trainViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    companion object {
        fun newInstance(): TrainFragment = TrainFragment()
    }
}