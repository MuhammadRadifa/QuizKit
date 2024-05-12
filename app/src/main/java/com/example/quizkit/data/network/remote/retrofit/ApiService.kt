package com.example.quizkit.data.network.remote.retrofit

import com.example.quizkit.data.network.remote.response.RegisterResponse
import retrofit2.http.POST

interface ApiService {
    @POST("users/register")
    suspend fun register(): RegisterResponse

    @POST("users/login")
    suspend fun login(): RegisterResponse
}