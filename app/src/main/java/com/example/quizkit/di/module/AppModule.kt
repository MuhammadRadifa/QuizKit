package com.example.quizkit.di.module

import com.example.quizkit.data.network.local.preferences.SettingPreferences
import com.example.quizkit.data.network.local.preferences.dataStore
import com.example.quizkit.ui.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val settingPreferences = SettingPreferences(androidContext().dataStore)
        settingPreferences
    }
    viewModel { MainViewModel(get()) }
}