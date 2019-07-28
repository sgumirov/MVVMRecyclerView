package com.gumirov.shamil.mvvmrecyclerview.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataLoadRepository {
    fun load(id: String): LiveData<String?> {
        val ret = MutableLiveData<String?>()
        Thread {
            for (i in 0..4) {
                Thread.sleep(800)
                ret.postValue("[$i] "+id)
            }
        }.start()
        return ret
    }
}
