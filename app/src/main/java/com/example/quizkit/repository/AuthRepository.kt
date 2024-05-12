package com.example.quizkit.repository

import com.example.quizkit.data.network.remote.retrofit.ApiService

class AuthRepository(
    private val apiService: ApiService
) {
    suspend fun register() = apiService.register()
    suspend fun login() = apiService.login()
}