package com.example.quizkit.repository

import com.example.quizkit.data.network.local.room.HistoryEntity
import com.example.quizkit.data.network.local.room.QuizDao
import com.example.quizkit.data.network.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow


class QuizRepository(
    private val apiService: ApiService,
    private val quizDao: QuizDao
) {

    suspend fun getQuiz(
        amount: Int,
        category: Int,
        difficulty: String?,
        type: String?
    ) = apiService.getQuiz(amount, category, difficulty, type)

    suspend fun insertHistory(
        historyEntity: HistoryEntity
    ) = quizDao.insertHistory(historyEntity)

    fun getHistory(): Flow<List<HistoryEntity>> = quizDao.getHistory()

    fun getLatestHistory():Flow<HistoryEntity> = quizDao.getLatestHistory()

    fun getHistoryById(id: Int): Flow<HistoryEntity> = quizDao.getHistoryById(id)
}