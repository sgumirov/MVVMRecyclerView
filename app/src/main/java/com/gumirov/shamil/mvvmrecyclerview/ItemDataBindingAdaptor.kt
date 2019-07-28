package com.gumirov.shamil.mvvmrecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemBindingViewHolder<ID, VM : ItemViewModel<ID>>
    constructor(private val binding: ViewDataBinding, createViewModel: (Int) -> VM,
                private val viewType: Int, private val bindingVarId: (Int) -> Int) :
    LifecycleOwner, RecyclerView.ViewHolder(binding.root) {

    val viewModel by lazy { createViewModel(viewType) } //yes, it's 1-1 VMs to ViewHolders. So no need for using Factory machinery
    protected val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun bind(item: ID) {
        viewModel.setId(item)
        binding.setVariable(bindingVarId(viewType), viewModel)
    }

    fun markAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
        binding.executePendingBindings()
    }

    fun markDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}

class ItemDataBindingAdaptor<I, VM : ItemViewModel<I>, B : ViewDataBinding>
    (private val itemLayout: (Int) -> Int,
     private val viewModelCreator: (Int) -> VM,
     private val bindingVarId: (Int) -> Int) :
    ListAdapter<I, RecyclerItemBindingViewHolder<I, VM>> (object : DiffUtil.ItemCallback<I>() {
        override fun areItemsTheSame(oldItem: I, newItem: I) = oldItem == newItem
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: I, newItem: I) = oldItem == newItem
    }) {

    private fun createBinding(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), itemLayout(viewType), parent, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemBindingViewHolder<I, VM> {
        val binding = createBinding(parent, viewType)
        val viewHolder = RecyclerItemBindingViewHolder<I, VM>(binding, viewModelCreator, viewType, bindingVarId)
        binding.lifecycleOwner = viewHolder
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerItemBindingViewHolder<I, VM>, position: Int) =
        holder.bind(getItem(position))

    override fun onViewAttachedToWindow(holder: RecyclerItemBindingViewHolder<I, VM>) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }
    override fun onViewDetachedFromWindow(holder: RecyclerItemBindingViewHolder<I, VM>) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }
}

