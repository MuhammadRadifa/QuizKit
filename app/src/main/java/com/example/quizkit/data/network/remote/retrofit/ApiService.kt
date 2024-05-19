package com.example.quizkit.data.network.remote.retrofit

import com.example.quizkit.data.network.remote.response.QuizResponse
import com.example.quizkit.data.network.remote.response.RegisterResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("users/register")
    suspend fun register(): RegisterResponse

    @POST("users/login")
    suspend fun login(): RegisterResponse

    @GET("api.php")
    suspend fun getQuiz(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String?,
        @Query("type") type: String?
    ): QuizResponse
}