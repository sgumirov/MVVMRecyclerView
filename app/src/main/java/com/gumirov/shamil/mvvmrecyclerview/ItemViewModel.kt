package com.gumirov.shamil.mvvmrecyclerview

/**
 * Contract for ViewModel. Data in ViewModel needs to be changed when RecyclerView item is reused for another id.
 */
interface ItemViewModel<ID> {
    fun setId(id: ID)
}
