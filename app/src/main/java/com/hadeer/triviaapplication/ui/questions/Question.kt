package com.hadeer.triviaapplication.ui.questions

data class Question(
    val id : Int,
    val question : String,
    val answers : List<String>,
    val correctAns : Int
)
