package com.example.ege_odev1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgotViewModel : ViewModel() {
    val currentEmail: MutableLiveData<String>? by lazy {
        MutableLiveData<String>()
    }
}