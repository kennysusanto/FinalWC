package com.example.finalwc.ui.station

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.finalwc.R

class StationFragment : Fragment() {

    private lateinit var homeViewModel: StationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(StationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_station, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })

        val recyclerView = root.findViewById(R.id.recycler_view) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(root.context, LinearLayout.VERTICAL, false)

        val trains = ArrayList<TrainData>()


        trains.add(
                TrainData(
                        "KRL00" + "5",
                        "Default Location",
                        "Station C",
                        "Station B",
                        17
                )
        )


        val adapter = CustomAdapter(trains)

        recyclerView.adapter = adapter

        return root
    }

    companion object {
        fun newInstance(): StationFragment = StationFragment()
    }
}