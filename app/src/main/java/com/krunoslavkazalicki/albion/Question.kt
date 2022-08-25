package com.krunoslavkazalicki.albion

data class Question(
    var questionText: String,
    var answers: List<String>,
    var correctAnswer: String)
