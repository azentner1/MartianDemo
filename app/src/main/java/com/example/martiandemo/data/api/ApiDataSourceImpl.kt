package com.example.martiandemo.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.martiandemo.data.db.models.*
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.security.auth.callback.Callback

class ApiDataSourceImpl(val apiService: ApiService) : ApiDataSource {

    private val TAG = ApiDataSourceImpl::class.java.name

    private val fetchedUsersTemp = MutableLiveData<List<User>>()

    override val fetchedUsers: LiveData<List<User>>
        get() = fetchedUsersTemp

    private val fetchedAlbumsTemp = MutableLiveData<List<Album>>()

    override val fetchedAlbums: LiveData<List<Album>>
        get() = fetchedAlbumsTemp

    private val fetchedPostsTemp = MutableLiveData<List<Post>>()

    override val fetchedPosts: LiveData<List<Post>>
        get() = fetchedPostsTemp

    private val createdPostTemp = MutableLiveData<Post>()

    override val createdPost: LiveData<Post>
        get() = createdPostTemp

    private val fetchedPhotosTemp = MutableLiveData<List<Photo>>()

    override val fetchedPhotos: LiveData<List<Photo>>
        get() = fetchedPhotosTemp

    private val fetchedTodosTemp = MutableLiveData<List<Todo>>()

    override val fetchedTodos: LiveData<List<Todo>>
        get() = fetchedTodosTemp

    private val fetchedCommentsTemp = MutableLiveData<List<Comment>>()

    override val fetchedComments: LiveData<List<Comment>>
        get() = fetchedCommentsTemp

    private val createdCommentTemp = MutableLiveData<Comment>()

    override val createdComment: LiveData<Comment>
        get() = createdCommentTemp

    override fun fetchUsers() {
            apiService.getUsers().enqueue(object : retrofit2.Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    val response = response.body()
                    fetchedUsersTemp.postValue(response)
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    val error = t.message
                }
            })
    }

    override fun fetchAlbums() {
        apiService.getAlbums().enqueue(object: retrofit2.Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                val response = response.body()
                fetchedAlbumsTemp.postValue(response)
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                val error = t.message
            }
        })
    }

    override fun fetchPosts() {
        apiService.getPosts().enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val response = response.body()
                fetchedPostsTemp.postValue(response)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                val error = t.message
            }
        })
    }

    override fun fetchPhotos(albumId: Int) {
        apiService.getPhotos(albumId).enqueue(object : retrofit2.Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val res = response.body()
                fetchedPhotosTemp.postValue(res)
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                val error = t.message
            }
        })
    }

    override fun fetchTodos(userId: Int) {
        apiService.getTodos(userId).enqueue(object : retrofit2.Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                val res = response.body()
                fetchedTodosTemp.postValue(res)
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                val error = t.message
            }
        })
    }

    override fun fetchComments(postId: Int) {
        apiService.getComments(postId).enqueue(object : retrofit2.Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val res = response.body()
                fetchedCommentsTemp.postValue(res)
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                val res = t.message
            }
        })
    }

    override fun createPost(post: Post) {
        apiService.createPost(post).enqueue(object : retrofit2.Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                val res = response.body()
                createdPostTemp.postValue(res)
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                val res = t.message
            }
        })
    }

    override fun createComment(comment: Comment) {
        apiService.createComment(comment).enqueue(object : retrofit2.Callback<Comment> {
            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                val res = response.body()
                createdCommentTemp.postValue(res)
            }

            override fun onFailure(call: Call<Comment>, t: Throwable) {
                val res = t.message
            }
        })
    }
}