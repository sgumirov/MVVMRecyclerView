# Databinding-aware RecyclerView

## Motivation

RecyclerView is the most used and one of the most complex Android components.

However with RecyclerView there's no simple way of:
* use Jetpack's [Data Binding|https://developer.android.com/topic/libraries/data-binding] library with view models backing items
* on-demand item data loading (see [Adaptive loading](.#2-adaptive-loading) below) - a problem for large memory usage
* without [infinite scroll|https://hackernoon.com/stop-infinite-scrolling-on-your-website-now-ie6rg31eu]
* without [pagination|https://www.bennadel.com/blog/2951-the-user-experience-ux-of-pagination.htm]

This library fills this gap.

## Value for user

A clear list with:
* real scrollbar
* no pagination
* items' content is loaded on demand.

## How to use



## Use case: load data for each item with ViewModel and display with data binding

`List<ID> -> adapter -> re-use ViewModel for ID on-demand -> load data and display`

With this library ViewModel becomes very lightweight:

```
class SampleItemViewModel(private val repo: DataLoadRepository): ViewModel(), ItemViewModel<String> {
    private val id = MutableLiveData<String>()

    val data = switchMap(id) { repo.load(it) }

    override fun setId(id: String) {
        this.id.postValue(id)
    }
}
```

## Adaptive loading

Obviously enough that loading items one-by-one does not make much sense: it is slow. However
loading is part of repository level. With proper caching and batch loading it should be fast
enough for most purposes.

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
