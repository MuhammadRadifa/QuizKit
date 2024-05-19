package com.example.quizkit.data.network.remote.response

data class ResponseState<T>(
    val loading: Boolean = true,
    val data: T? = null,
    val error: String? = null
)