package com.example.finalwc.ui.station

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class StationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Station Name"
    }
    val text: LiveData<String> = _text
}