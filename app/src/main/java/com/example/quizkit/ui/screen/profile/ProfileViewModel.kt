package com.example.quizkit.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizkit.data.network.local.room.UserEntity
import com.example.quizkit.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    fun updateAvatar(email: String, avatar: Int, background: Int) {
        viewModelScope.launch {
            repository.updateAvatar(email, background, avatar)
        }
    }

    fun getUser(email:String): Flow<UserEntity> {
        return repository.getUser(email)
    }

}