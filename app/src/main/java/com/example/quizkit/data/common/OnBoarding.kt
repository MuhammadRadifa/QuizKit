package com.example.quizkit.data.common

data class OnBoarding(
    val title:String = "",
    val description:String = ""
)

val onBoardingData = mapOf<Int, OnBoarding>(
    0 to OnBoarding("",""),
    1 to OnBoarding("",""),
    2 to OnBoarding("","")
)