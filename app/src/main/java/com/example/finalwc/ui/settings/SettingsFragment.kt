package com.example.finalwc.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.widget.Button
import android.widget.Toast
import com.example.finalwc.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(), View.OnClickListener {

    private lateinit var notificationsViewModel: SettingsViewModel

    var dbHandler: DatabaseHandler? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val textView: TextView = root.findViewById(R.id.text_settings)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })

        dbHandler = DatabaseHandler(root.context)

        val btn_save: Button = root.findViewById(R.id.button_save)
        btn_save.setOnClickListener(this)

        val btn_show: Button = root.findViewById(R.id.button_show)
        btn_show.setOnClickListener(this)

        val btn_clear: Button = root.findViewById(R.id.button_clear_table)
        btn_clear.setOnClickListener(this)

        return root
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_save -> {
                if (validation()) {
                    val train = TrainObject()
                    var success = false
                    train.train_id = editText_train_id.text.toString()
                    train.train_curr_loc = editText_train_curr_loc.text.toString()
                    train.prev_station_id = editText_prev_station_id.text.toString()
                    train.next_station_id = editText_next_station_id.text.toString()
                    train.arrival_eta = Integer.parseInt(editText_arrival_eta.text.toString())

                    success = dbHandler!!.addTrain(train)

                    if (success) {
                        val toast = Toast.makeText(
                            activity?.applicationContext,
                            "Saved Successfully!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            R.id.button_show -> {
                var train = dbHandler!!.getAllTrain()
                textView_allTrain.setText(train)
            }
            R.id.button_clear_table ->{
                dbHandler!!.deleteFromTable()
                val toast = Toast.makeText(activity?.applicationContext, "Table Cleared!", Toast.LENGTH_LONG).show()
                }
            }
        }



    fun validation(): Boolean{
        var validate = false
        if(!editText_train_id.text.toString().equals("") && !editText_train_curr_loc.text.toString().equals("")){
            validate = true
        }else{
            validate = false
            val toast = Toast.makeText(activity?.applicationContext, "Fill all details", Toast.LENGTH_LONG).show()
        }
        return validate
    }
}