package com.example.quizkit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.quizkit.data.network.local.preferences.SettingPreferences
import kotlinx.coroutines.launch

class MainViewModel(
    private val settingPreferences: SettingPreferences
):ViewModel(){

    fun getToken() = settingPreferences.getToken().asLiveData()

    fun saveToken(token:String) {
        viewModelScope.launch {
            settingPreferences.saveToken(token)
        }
    }

}