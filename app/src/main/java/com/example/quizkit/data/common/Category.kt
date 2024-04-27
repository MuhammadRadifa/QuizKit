package com.example.quizkit.data.common

import com.example.quizkit.R

data class Category(
    val title:String = "",
    val amountQuiz:Int = 0,
    val icon:Int = 0
)

val itemCategory = listOf<Category>(
    Category(
        title = "Entertaiment",
        amountQuiz = 4,
        icon = R.drawable.entertaiment
    ),
    Category(
        title = "Education",
        amountQuiz = 4,
        icon = R.drawable.education
    ),
    Category(
        title = "Technology",
        amountQuiz = 4,
        icon = R.drawable.tech
    ),
    Category(
        title = "Art",
        amountQuiz = 4,
        icon = R.drawable.art
    ),
    Category(
        title = "Science",
        amountQuiz = 4,
        icon = R.drawable.science
    ),
    Category(
        title = "Showbiz",
        amountQuiz = 4,
        icon = R.drawable.showbiz
    )
)