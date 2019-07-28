package com.gumirov.shamil.mvvmrecyclerview.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gumirov.shamil.mvvmrecyclerview.ItemViewModel

class SampleItemViewModel(private val repo: DataLoadRepository): ViewModel(), ItemViewModel<String> {
    val id = MutableLiveData<String>()

    val data = Transformations.switchMap(id) { repo.load(it) }

    override fun setId(id: String) {
        this.id.postValue(id)
    }
}
