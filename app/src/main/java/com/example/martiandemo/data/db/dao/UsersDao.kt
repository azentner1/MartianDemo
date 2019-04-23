package com.example.martiandemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.martiandemo.data.db.models.User

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(users: List<User>)

//    @Query("SELECT * FROM users WHERE id = &id")
//    fun getUser(): LiveData<User>

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<User>>
}