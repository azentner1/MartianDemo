package com.example.martiandemo.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String) {

    constructor(postId: Int, name: String, email: String, body: String) : this(0, postId, name, email, body)
}