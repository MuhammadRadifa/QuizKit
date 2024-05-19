package com.example.quizkit.data.network.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class,UserEntity::class], version = 1, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun QuizDao(): QuizDao

    abstract fun UserDao(): UserDao
}