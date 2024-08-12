package com.oscargil80.groceryapproommvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroceryViewModalFactory(private val repository: GroceryRepository): ViewModelProvider.NewInstanceFactory() {

    override  fun <T: ViewModel?> create(modalClass: Class<T>):T{
        return GroceryViewModal(repository) as T
    }
}