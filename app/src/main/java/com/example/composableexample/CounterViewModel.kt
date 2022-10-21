package com.example.composableexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    fun onCountChanged(newCount: Int) {
        _count.value = newCount
    }
}
