package com.example.quizkit.repository

import com.example.quizkit.data.network.local.preferences.SettingPreferences
import com.example.quizkit.data.network.local.room.UserDao
import com.example.quizkit.data.network.local.room.UserEntity

class AuthRepository(
    private val userDao: UserDao,
    private val preferences: SettingPreferences
) {
    suspend fun register(userEntity: UserEntity) = userDao.register(userEntity)
    fun getUser(email: String) = userDao.getUser(email)
    suspend fun updateAvatar(email: String, background: Int, avatar: Int) = userDao.updateAvatar(email, background, avatar)


    suspend fun saveToken(token: String) = preferences.saveToken(token)
    fun getToken() = preferences.getToken()
    suspend fun clearToken() = preferences.clearToken()
}