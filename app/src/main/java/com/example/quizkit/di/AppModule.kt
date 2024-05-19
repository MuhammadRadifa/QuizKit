package com.example.quizkit.di

import androidx.room.Room
import com.example.quizkit.data.network.local.preferences.SettingPreferences
import com.example.quizkit.data.network.local.preferences.dataStore
import com.example.quizkit.data.network.local.room.QuizDatabase
import com.example.quizkit.data.network.remote.retrofit.ApiConfig
import com.example.quizkit.repository.AuthRepository
import com.example.quizkit.repository.QuizRepository
import com.example.quizkit.ui.MainViewModel
import com.example.quizkit.ui.screen.authentication.AuthViewModel
import com.example.quizkit.ui.screen.history.HistoryViewModel
import com.example.quizkit.ui.screen.home.HomeViewModel
import com.example.quizkit.ui.screen.profile.ProfileViewModel
import com.example.quizkit.ui.screen.quiz.QuizViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        val settingPreferences = SettingPreferences(androidContext().dataStore)
        settingPreferences
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            QuizDatabase::class.java, "quiz.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { get<QuizDatabase>().QuizDao() }
    single { get<QuizDatabase>().UserDao() }
}

val networkModule = module {
    single {
        val apiService = ApiConfig.getApiService()
        apiService
    }
}

val repositoryModule = module {
    single { AuthRepository(get(),get()) }
    single { QuizRepository(get(),get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { HomeViewModel(get(),get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}
