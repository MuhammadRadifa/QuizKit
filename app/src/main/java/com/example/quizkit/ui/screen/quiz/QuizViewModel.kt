package com.example.quizkit.ui.screen.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizkit.data.common.SettingsQuiz
import com.example.quizkit.data.network.local.room.HistoryEntity
import com.example.quizkit.data.network.remote.response.QuizResponse
import com.example.quizkit.data.network.remote.response.ResponseState
import com.example.quizkit.data.network.remote.response.ResultsItem
import com.example.quizkit.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    private val _quiz = MutableStateFlow(ResponseState<List<ResultsItem>>())
    val quiz:StateFlow<ResponseState<List<ResultsItem>>> = _quiz

    private val _history = MutableStateFlow(ResponseState<HistoryEntity>())
    val history:StateFlow<ResponseState<HistoryEntity>> = _history

    private val _getLatestHistory = MutableStateFlow(ResponseState<HistoryEntity>())
    val getLatestHistory:StateFlow<ResponseState<HistoryEntity>> = _getLatestHistory

    fun getQuiz(settings: SettingsQuiz) {
        viewModelScope.launch {
            try {
                val response = quizRepository.getQuiz(
                    amount = settings.amount,
                    category = settings.category,
                    difficulty = if (settings.difficulty == "Mixed") null else settings.difficulty,
                    type = if (settings.type == "Mixed") null else settings.type
                )
                if (response.responseCode == 0) {
                    _quiz.value = _quiz.value.copy(
                        loading = false,
                        data = response.results,
                        error = null
                    )
                    Log.i("QuizViewModel", "getQuiz: ${response.results}")

                }else{
                    _quiz.value = _quiz.value.copy(
                        loading = false,
                        data = null,
                        error = "Error Null Data"
                    )
                }

            } catch (e: Exception) {
                _quiz.value = _quiz.value.copy(
                    loading = false,
                    data = null,
                    error = e.message
                )
            }
        }
    }

    fun insertHistory(historyEntity: HistoryEntity){
        viewModelScope.launch {
            quizRepository.insertHistory(historyEntity)
        }
    }

    fun getLatestHistory(){
        viewModelScope.launch {
            quizRepository.getLatestHistory().collect {
                _getLatestHistory.value = _getLatestHistory.value.copy(
                    loading = false,
                    data = it,
                    error = null
                )
            }
        }
    }

    fun getHistoryById(id: Int){
        viewModelScope.launch {
            quizRepository.getHistoryById(id).collect {
                _history.value = _history.value.copy(
                    loading = false,
                    data = it,
                    error = null
                )
            }
        }
    }

}