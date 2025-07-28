package com.hadeer.triviaapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class QuestionViewModel:ViewModel() {
    private val questiions = QuestionsData.getRandom()

    private val _currentIndx = MutableLiveData(0)
    var curentIndex : LiveData<Int> = _currentIndx
    private val _currentResult = MutableLiveData(0)
    val currentResult : LiveData<Int> = _currentResult

    val currentQuestion : LiveData<Question> = curentIndex.map { questiions[it] }

    fun goToNextQuestion(){
        if(_currentIndx.value!! < questiions.lastIndex){
            _currentIndx.value = _currentIndx.value?.plus(1)
        }
    }

    fun updateResult(){
        _currentResult.value = _currentResult.value?.plus(1)
        println("updated answer ${_currentResult.value} size is ${questiions.size}")
    }
}