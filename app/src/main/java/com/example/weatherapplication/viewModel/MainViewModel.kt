package com.example.weatherapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel  : ViewModel() {

    private val updateListData  = MutableLiveData(false)

    fun getUpdateFlag(): LiveData<Boolean>{
        return updateListData
    }

    fun setUpdateFlag(doUpdate: Boolean){
        updateListData.postValue(doUpdate)
    }
}