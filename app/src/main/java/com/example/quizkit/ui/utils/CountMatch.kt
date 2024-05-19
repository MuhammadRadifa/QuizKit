package com.example.quizkit.ui.utils

fun countMatchingElements(array1: List<String>, array2: List<String>): Int {
    val minLength = minOf(array1.size, array2.size)
    var matchCount = 0

    for (i in 0 until minLength) {
        if (array1[i] == array2[i]) {
            matchCount++
        }
    }

    return matchCount
}