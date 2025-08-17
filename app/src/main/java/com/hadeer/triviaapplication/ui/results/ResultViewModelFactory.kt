package com.hadeer.triviaapplication.ui.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//where to define how can create our Result ViewModel
class ResultViewModelFactory (private val finalScore : Int): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java)){
            return ResultViewModel(finalScore) as T
        }
        throw  IllegalArgumentException("Unknow ViewModel class")
    }
}