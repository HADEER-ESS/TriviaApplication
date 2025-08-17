package com.hadeer.triviaapplication.ui.results

import android.util.Log
import androidx.lifecycle.ViewModel

class ResultViewModel (finalScore : Int): ViewModel() {
    init {
        Log.i("Result View Model" , "Final score is $finalScore")
    }
}