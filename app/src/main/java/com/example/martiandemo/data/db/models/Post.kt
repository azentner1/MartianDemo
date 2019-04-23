package com.example.martiandemo.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String) {

    constructor(userId: Int, title: String, body: String) : this(0, userId, title, body)
}