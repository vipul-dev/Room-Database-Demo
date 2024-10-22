package com.vipul.roomdatabase.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vipul.roomdatabase.database.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * From users")
    suspend fun getAllUsers():List<User>

}