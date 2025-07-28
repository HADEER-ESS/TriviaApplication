package com.hadeer.triviaapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class QuestionViewModel:ViewModel() {
    private val questiions = QuestionsData.getRandom()

    private val _curentIndx = MutableLiveData(0)
    var curentIndex : LiveData<Int> = _curentIndx

    val currentQuestion : LiveData<Question> = curentIndex.map { questiions[it] }

    fun goToNextQuestion(){
        if(_curentIndx.value!! < questiions.lastIndex){
            _curentIndx.value = _curentIndx.value?.plus(1)
        }
    }
}