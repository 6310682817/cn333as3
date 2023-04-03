package com.example.multigame.data.MathQuizQuestion

data class Question(
    val question: String,
    var options: List<String>,
    val correctAnswer: String
)