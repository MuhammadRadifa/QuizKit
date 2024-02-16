package com.example.quizkit.data

import com.example.quizkit.R

data class NavigationItem(
    val title:String,
    val icon:Int,
)

val itemNavigation = listOf<NavigationItem>(
    NavigationItem(
        title = "Home",
        icon = R.drawable.home
    ),
    NavigationItem(
        title = "Category",
        icon = R.drawable.categories
    ),
    NavigationItem(
        title = "WhiteSpace",
        icon = R.drawable.rocket
    ),
    NavigationItem(
        title = "History",
        icon = R.drawable.history
    ),
    NavigationItem(
        title = "Profile",
        icon = R.drawable.profile
    )
)