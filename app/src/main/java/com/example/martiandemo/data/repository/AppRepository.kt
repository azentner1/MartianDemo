package com.example.martiandemo.data.repository

import androidx.lifecycle.LiveData
import com.example.martiandemo.data.db.models.*


interface AppRepository {

    suspend fun getUsers(): LiveData<List<User>>

    suspend fun getAlbums(): LiveData<List<Album>>

    suspend fun getPosts(): LiveData<List<Post>>

    suspend fun getPhotos(albumId: Int): LiveData<List<Photo>>

    suspend fun getTodos(userId: Int): LiveData<List<Todo>>

    suspend fun getComments(postId: Int): LiveData<List<Comment>>

    suspend fun createPost(post: Post)

    suspend fun createComment(comment: Comment)


}