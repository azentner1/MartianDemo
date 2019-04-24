package com.example.martiandemo.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.martiandemo.data.api.ApiDataSource
import com.example.martiandemo.data.db.dao.*
import com.example.martiandemo.data.db.models.*
import kotlinx.coroutines.*

class AppRepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val usersDao: UsersDao,
    private val albumsDao: AlbumsDao,
    private val postsDao: PostsDao,
    private val photoDao: PhotoDao,
    private val todosDao: TodosDao,
    private val commentsDao: CommentsDao
) : AppRepository {

    init {
        apiDataSource.fetchedUsers.observeForever{
            saveFetchedUsers(it)
        }
        apiDataSource.fetchedAlbums.observeForever {
            saveFetchedAlbums(it)
        }

        apiDataSource.fetchedPosts.observeForever {
            saveFetchedPosts(it)
        }

        apiDataSource.fetchedPhotos.observeForever {
            saveFetchedPhotos(it)
        }

        apiDataSource.fetchedTodos.observeForever {
            saveFetchedTodos(it)
        }

        apiDataSource.fetchedComments.observeForever {
            saveFetchedComments(it)
        }

        apiDataSource.createdPost.observeForever {
            saveFetchedPost(it)
        }

        apiDataSource.createdComment.observeForever {
            saveFetchedComment(it)
        }
    }

    private val createdComment = MutableLiveData<Comment>()
    private val createdPost = MutableLiveData<Post>()

    override suspend fun getUsers(): LiveData<List<User>> {
        fetchUserData()
        return withContext(Dispatchers.IO) {
            return@withContext usersDao.getUsers()
        }
    }

    private fun saveFetchedUsers(users: List<User>) {
        GlobalScope.launch(Dispatchers.IO) {
            usersDao.insertOrUpdate(users)
        }
    }

    private fun fetchUserData() {
        apiDataSource.fetchUsers()
    }

    override suspend fun getAlbums(): LiveData<List<Album>> {
        fetchAlbumData()
        return withContext(Dispatchers.IO) {
            return@withContext albumsDao.getAlbums()
        }
    }

    private fun fetchAlbumData() {
        apiDataSource.fetchAlbums()
    }

    private fun saveFetchedAlbums(albums: List<Album>) {
        GlobalScope.launch(Dispatchers.IO) {
            albumsDao.insertOrUpdate(albums)
        }
    }

    override suspend fun getPosts(): LiveData<List<Post>> {
        fetchPostData()
        return withContext(Dispatchers.IO) {
            return@withContext postsDao.getPosts()
        }
    }

    private fun fetchPostData() {
        apiDataSource.fetchPosts()
    }

    private fun saveFetchedPosts(posts: List<Post>) {
        GlobalScope.launch(Dispatchers.IO) {
            postsDao.updateOrInsert(posts)
        }
    }

    override suspend fun getPhotos(albumId: Int): LiveData<List<Photo>> {
        fetchPhotosData(albumId)
        return withContext(Dispatchers.IO) {
            return@withContext photoDao.getPhotos()
        }
    }

    private fun fetchPhotosData(albumId: Int) {
        apiDataSource.fetchPhotos(albumId)
    }

    private fun saveFetchedPhotos(photos: List<Photo>) {
        GlobalScope.launch(Dispatchers.IO) {
            photoDao.insertOrUpdate(photos)
        }
    }


    override suspend fun getTodos(userId: Int): LiveData<List<Todo>> {
        fetchTodosData(userId)
        return withContext(Dispatchers.IO) {
            return@withContext todosDao.getTodos(userId)
        }
    }

    private fun fetchTodosData(userId: Int) {
        apiDataSource.fetchTodos(userId)
    }

    private fun saveFetchedTodos(todos: List<Todo>) {
        GlobalScope.launch(Dispatchers.IO) {
            todosDao.insertOrUpdate(todos)
        }
    }

    override suspend fun getComments(postId: Int): LiveData<List<Comment>> {
        fetchCommentsData(postId)
        return withContext(Dispatchers.IO) {
            return@withContext commentsDao.getComments(postId)
        }
    }

    private fun fetchCommentsData(postId: Int) {
        apiDataSource.fetchComments(postId)
    }

    private fun saveFetchedComments(comments: List<Comment>) {
        GlobalScope.launch(Dispatchers.IO) {
            commentsDao.insertOrUpdate(comments)
        }
    }



    override fun saveFetchedPost(post: Post) {
        GlobalScope.launch(Dispatchers.IO) {
            postsDao.updateOrInsert(post)
        }
        createdPost.value = post
        getSavedPost()
    }

    override fun getSavedPost() : LiveData<Post> {
        return createdPost
    }

    override suspend fun savePost(post: Post) {
        apiDataSource.createPost(post)
    }

    override fun saveFetchedComment(comment: Comment) {
        GlobalScope.launch(Dispatchers.IO) {
            commentsDao.updateOrInsert(comment)
        }
        createdComment.value = comment
        getSavedComment()
    }

    override fun getSavedComment() : LiveData<Comment> {
        return createdComment
    }

    override suspend fun saveComment(comment: Comment) {
        apiDataSource.createComment(comment)
    }
}