package com.example.quizkit.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.quizkit.data.network.local.room.HistoryEntity
import com.example.quizkit.data.network.local.room.UserEntity
import com.example.quizkit.repository.AuthRepository
import com.example.quizkit.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val quizRepository: QuizRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun getLatestHistory(): Flow<HistoryEntity> {
        return quizRepository.getLatestHistory()
    }

    fun getUser(email:String): Flow<UserEntity> {
        return authRepository.getUser(email)
    }
}