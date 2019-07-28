# MVVM databinding-aware RecyclerView Adapter

MVVM data binding aware implementation of RecyclerView's Adapter class.

Use-case: load data for each item using ViewModel and databinding. Takes care of
lifecycle.

Usage:

`List<ID> -> adapter -> binds ViewModel for id -> loads data`

ViewModel can be implemented like:

```
class SampleItemViewModel(private val repo: DataLoadRepository): ViewModel(), ItemViewModel<String> {
    val id = MutableLiveData<String>()

    val data = Transformations.switchMap(id) { repo.load(it) }

    override fun setId(id: String) {
        this.id.postValue(id)
    }
}
```

Author:
Shamil Gumirov
shamilg1 a-t gmail.com

License: Apache 2.0. As-is, use on your own risk.
