package com.gumirov.shamil.mvvmrecyclerview.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val _clicked = MutableLiveData<Int>().also { it.value = 0 }
    val clicked: LiveData<Int> = _clicked

    fun click() {
        val c = _clicked.value ?: 0
        _clicked.postValue(c+1)
    }
}
