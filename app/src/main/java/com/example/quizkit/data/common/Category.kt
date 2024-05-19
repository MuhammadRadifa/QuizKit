package com.example.quizkit.data.common

import com.example.quizkit.R

sealed class CategoryQuiz(val title:String){
    data object Education : CategoryQuiz("Education")
    data object Entertainment : CategoryQuiz("Entertainment")
    data object Technology : CategoryQuiz("Technology")
    data object Art : CategoryQuiz("Art")
    data object Science : CategoryQuiz("Science")
    data object Showbiz : CategoryQuiz("Showbiz")
}

data class Category(
    val title:String = "",
    val amountQuiz:Int = 0,
    val icon:Int = 0
)

val iconCategory = mapOf(
    CategoryQuiz.Entertainment.title to R.drawable.entertaiment,
    CategoryQuiz.Education.title to R.drawable.education,
    CategoryQuiz.Technology.title to R.drawable.tech,
    CategoryQuiz.Art.title to R.drawable.art,
    CategoryQuiz.Science.title to R.drawable.science,
    CategoryQuiz.Showbiz.title to R.drawable.showbiz
)

val itemCategory = listOf<Category>(
    Category(
        title = CategoryQuiz.Entertainment.title,
        amountQuiz = 4,
        icon = R.drawable.entertaiment
    ),
    Category(
        title = CategoryQuiz.Education.title,
        amountQuiz = 5,
        icon = R.drawable.education
    ),
    Category(
        title = CategoryQuiz.Technology.title,
        amountQuiz = 2,
        icon = R.drawable.tech
    ),
    Category(
        title = CategoryQuiz.Art.title,
        amountQuiz = 1,
        icon = R.drawable.art
    ),
    Category(
        title = CategoryQuiz.Science.title,
        amountQuiz = 2,
        icon = R.drawable.science
    ),
    Category(
        title = CategoryQuiz.Showbiz.title,
        amountQuiz = 8,
        icon = R.drawable.showbiz
    )
)