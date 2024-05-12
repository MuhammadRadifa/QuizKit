package com.example.quizkit.data.network.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: DataLogin
)

data class DataLogin(

	@field:SerializedName("background")
	val background: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("picture")
	val picture: Int,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("token")
	val token: String
)
