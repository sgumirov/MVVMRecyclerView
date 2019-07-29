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

## TODOs

Use annotations for less boilerplate.

### License and copyright

Copyright (c) 2019 by Shamil Gumirov

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License.
