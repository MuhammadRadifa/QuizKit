package com.example.quizkit

import android.app.Application
import com.example.quizkit.di.appModule
import com.example.quizkit.di.networkModule
import com.example.quizkit.di.repositoryModule
import com.example.quizkit.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }
    }
}