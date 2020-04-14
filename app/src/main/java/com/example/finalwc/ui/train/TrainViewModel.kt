package com.example.finalwc.ui.train

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class TrainViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is train Fragment"
    }
    val text: LiveData<String> = _text
}