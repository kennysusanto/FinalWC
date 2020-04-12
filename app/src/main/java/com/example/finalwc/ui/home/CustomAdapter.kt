package com.example.finalwc.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.finalwc.R

class CustomAdapter(val trainDataList: ArrayList<TrainData>): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(trainDataList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return trainDataList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(trainData: TrainData){
            val text_train_id = itemView.findViewById(R.id.text_train_id) as TextView
            val text_departure_eta = itemView.findViewById(R.id.text_departure_eta) as TextView
            val text_next_station = itemView.findViewById(R.id.text_next_station) as TextView
            text_train_id.text = trainData.train_id
            text_departure_eta.text = "Arr ETA: " + trainData.arrival_eta.toString() + " minutes"
            text_next_station.text = "Last Station: " + trainData.next_station_id
        }
    }
}