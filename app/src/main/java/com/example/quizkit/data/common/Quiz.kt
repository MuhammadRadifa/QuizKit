package com.example.quizkit.data.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsQuiz(
    val amount: Int = 10,
    val category: Int = 9,
    val difficulty: String = "Mixed",
    val type: String = "multiple"
):Parcelable