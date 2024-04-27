package com.example.quizkit.data.common

import com.example.quizkit.R

data class Profile(
    val name:String = "Guest",
    val avatar:Int = 0,
    val background:Int =0
)

val avatarProfile = mapOf<Int,Int>(
    0 to R.drawable.boy_1,
    1 to R.drawable.girl_1,
    2 to R.drawable.girl_2,
    3 to R.drawable.boy_2,
    4 to R.drawable.boy_3,
    5 to R.drawable.girl_3,
    6 to R.drawable.boy_4,
    7 to R.drawable.girl_4,
)

val backgroundProfile = mapOf<Int,Int>(
    0 to R.color.skin_background,
    1 to R.color.gray_background,
    2 to R.color.red_background,
    3 to R.color.aqua_background,
    4 to R.color.brown_background,
    5 to R.color.orange_background,
    6 to R.color.yellow_background,
    7 to R.color.green_background,
)