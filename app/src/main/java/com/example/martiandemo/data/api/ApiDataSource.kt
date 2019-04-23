package com.example.martiandemo.data.api

import androidx.lifecycle.LiveData
import com.example.martiandemo.data.db.models.*


interface ApiDataSource {

    val fetchedUsers: LiveData<List<User>>
    fun fetchUsers()

    val fetchedAlbums: LiveData<List<Album>>
    fun fetchAlbums()

    val fetchedPosts: LiveData<List<Post>>
    fun fetchPosts()

    val fetchedPhotos: LiveData<List<Photo>>
    fun fetchPhotos(albumId: Int)

    val fetchedTodos: LiveData<List<Todo>>
    fun fetchTodos(userId: Int)

    val fetchedComments: LiveData<List<Comment>>
    fun fetchComments(postId: Int)

    val createdPost: LiveData<Post>
    fun createPost(post: Post)

    val createdComment: LiveData<Comment>
    fun createComment(comment: Comment)
}