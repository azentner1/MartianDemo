package com.example.martiandemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.martiandemo.data.db.models.Comment


@Dao
interface CommentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(comments: List<Comment>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrInsert(comment: Comment)

    @Query("SELECT * FROM comments WHERE postId = :post_id ORDER BY id DESC")
    fun getComments(post_id: Int): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE id = :comment_id")
    fun getComment(comment_id: Int): LiveData<Comment>

}