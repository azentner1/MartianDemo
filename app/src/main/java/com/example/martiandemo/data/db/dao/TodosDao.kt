package com.example.martiandemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.martiandemo.data.db.models.Todo

@Dao
interface TodosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(photos: List<Todo>)

    @Query("SELECT * FROM todos WHERE userId = :user_id")
    fun getTodos(user_id: Int) : LiveData<List<Todo>>
}