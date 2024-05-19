package com.example.quizkit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.quizkit.data.network.local.preferences.SettingPreferences
import com.example.quizkit.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val settingPreferences: SettingPreferences,
):ViewModel(){

    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token

    init {
        getToken()
    }

    fun getToken(){
        viewModelScope.launch {
            settingPreferences.getToken().collect {
                _token.value = it
            }
        }
    }

    fun saveToken(token:String) {
        viewModelScope.launch {
            settingPreferences.saveToken(token)
        }
    }

}