package com.example.quizkit.data.network.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: HistoryEntity)

    //select history from database
    @Query("SELECT * FROM history ORDER BY id DESC")
    fun getHistory(): Flow<List<HistoryEntity>>

    //select latest history from database
    @Query("SELECT * FROM history ORDER BY id DESC LIMIT 1")
    fun getLatestHistory(): Flow<HistoryEntity>

    //get history by id
    @Query("SELECT * FROM history WHERE id = :id")
    fun getHistoryById(id: Int): Flow<HistoryEntity>
}

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun register(user: UserEntity)

    //select user from database
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    fun getUser(email: String): Flow<UserEntity>

    //update avatar and background
    @Query("UPDATE user SET avatar = :avatar,background = :background WHERE email = :email")
    suspend fun updateAvatar(email: String, background: Int, avatar: Int)


}