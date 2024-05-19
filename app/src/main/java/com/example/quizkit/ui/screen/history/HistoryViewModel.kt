package com.example.quizkit.ui.screen.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizkit.data.network.local.room.HistoryEntity
import com.example.quizkit.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HistoryViewModel(
    private val repository: QuizRepository
): ViewModel(){

    fun getHistory(): Flow<List<HistoryEntity>> {
        return repository.getHistory()
    }

}