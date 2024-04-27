package com.example.quizkit.data.common

import com.example.quizkit.R

data class NavigationItem(
    val title:String,
    val icon:Int,
)

val itemNavigation = listOf<NavigationItem>(
    NavigationItem(
        title = Screen.Home.route,
        icon = R.drawable.home
    ),
    NavigationItem(
        title = Screen.Category.route,
        icon = R.drawable.categories
    ),
    NavigationItem(
        title = "WhiteSpace",
        icon = R.drawable.rocket
    ),
    NavigationItem(
        title = Screen.History.route,
        icon = R.drawable.history
    ),
    NavigationItem(
        title = Screen.Profile.route,
        icon = R.drawable.profile
    )
)