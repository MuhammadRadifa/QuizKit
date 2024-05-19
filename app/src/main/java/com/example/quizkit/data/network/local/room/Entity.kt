package com.example.quizkit.data.network.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "quiz")
    val quiz: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "correctAnswer")
    val correctAnswer: Int,
    @ColumnInfo(name = "size")
    val size: Int,
)

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "avatar")
    val avatar: Int,
    @ColumnInfo(name = "background")
    val background: Int,
)