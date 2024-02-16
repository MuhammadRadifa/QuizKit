package com.example.quizkit.ui.utils


import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun DateConverter(currentMilliseconds: Long): String {
    val sdf = SimpleDateFormat("dd MMMM yyyy")

    val currentDate = Date(currentMilliseconds)

    return sdf.format(currentDate)
}