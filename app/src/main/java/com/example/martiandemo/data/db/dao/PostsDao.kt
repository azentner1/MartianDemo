package com.example.martiandemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.martiandemo.data.db.models.Post

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrInsert(posts: List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrInsert(post: Post)

    @Query("SELECT * FROM posts ORDER BY id DESC")
    fun getPosts(): LiveData<List<Post>>


    @Query("SELECT * FROM posts WHERE id = :post_id")
    fun getPost(post_id: Int): LiveData<Post>
}