package com.example.quizkit.data.network.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("data")
	val data: DataRegister
)

data class DataRegister(

	@field:SerializedName("background")
	val background: Int = 0,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("picture")
	val picture: Int = 0,

	@field:SerializedName("username")
	val username: String
)
