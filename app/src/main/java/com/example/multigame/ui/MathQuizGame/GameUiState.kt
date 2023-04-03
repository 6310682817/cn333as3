package com.example.multigame.ui.MathQuizGame

import com.example.multigame.data.MathQuizQuestion.Question

data class GameUiState(
    val currentQuestionCount: Int = 1,
    val currentQuestion: Question,
    val score: Int = 0,
    val isGameOver: Boolean = false,
    val selectedAnswer: String = "",
    val result: String = "",
)