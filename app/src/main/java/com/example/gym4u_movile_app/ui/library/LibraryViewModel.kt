package com.example.gym4u_movile_app.ui.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LibraryViewModel : ViewModel(){
    private val _text = MutableLiveData<String>().apply {
        value = "This is Library Fragme"
    }
    val text: LiveData<String> = _text
}