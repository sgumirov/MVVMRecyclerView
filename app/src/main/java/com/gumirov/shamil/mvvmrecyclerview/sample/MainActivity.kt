package com.gumirov.shamil.mvvmrecyclerview.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gumirov.shamil.mvvmrecyclerview.ItemDataBindingAdaptor
import com.gumirov.shamil.mvvmrecyclerview.R
import com.gumirov.shamil.mvvmrecyclerview.databinding.ActivityMainBinding
import com.gumirov.shamil.mvvmrecyclerview.databinding.TestItemBinding

class MainActivity : AppCompatActivity() {

    val repo = DataLoadRepository()

    val ids = (0..500).toList().map { it.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val adapter = ItemDataBindingAdaptor<String, SampleItemViewModel,
                TestItemBinding>({ R.layout.test_item }, { SampleItemViewModel(repo) }, { BR.item})
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter

        viewModel.clicked.observe(this, Observer {
            if (it == 1) {
                adapter.submitList(ids)
            }
        })
    }
}
