package com.example.finalwc.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.finalwc.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })

        val recyclerView = root.findViewById(R.id.recycler_view) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(root.context, LinearLayout.VERTICAL, false)

        val trains = ArrayList<TrainData>()


        trains.add(
            TrainData(
                "KRL00" + (0..100).random(),
                "Default Location",
                randStat(),
                randStat(),
                (0..30).random()
            )
        )



        val adapter = CustomAdapter(trains)

        recyclerView.adapter = adapter

        return root
    }
    fun randStat():String{
        var result = ""

        var seed = (0..3).random()

        if(seed == 0) result = "Station A"
        else if(seed == 1) result = "Station B"
        else result = "Station C"

        return result
    }





}