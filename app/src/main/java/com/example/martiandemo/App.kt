package com.example.martiandemo

import android.app.Application
import com.example.martiandemo.data.api.ApiDataSource
import com.example.martiandemo.data.api.ApiDataSourceImpl
import com.example.martiandemo.data.api.ApiService
import com.example.martiandemo.data.db.AppDatabase
import com.example.martiandemo.data.db.dao.*
import com.example.martiandemo.data.repository.AppRepository
import com.example.martiandemo.data.repository.AppRepositoryImpl
import com.example.martiandemo.ui.albums.AlbumsViewModelFactory
import com.example.martiandemo.ui.albums.photo_list.PhotoListViewModelFactory
import com.example.martiandemo.ui.albums.photo_list.photo.PhotoViewModelFactory
import com.example.martiandemo.ui.posts.PostsViewModelFactory
import com.example.martiandemo.ui.posts.comments.CommentsViewHolderFactory
import com.example.martiandemo.ui.posts.comments.create.CreateCommentViewModelFactory
import com.example.martiandemo.ui.posts.create.CreatePostViewModelFactory
import com.example.martiandemo.ui.users.todos.TodoViewModelFactory
import com.example.martiandemo.ui.users.UsersViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*


class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind<AppDatabase>() with singleton { AppDatabase(instance()) }
        bind<UsersDao>() with singleton { instance<AppDatabase>().usersDao() }
        bind<AlbumsDao>() with singleton { instance<AppDatabase>().albumsDao() }
        bind<PostsDao>() with singleton { instance<AppDatabase>().postsDao() }
        bind<PhotoDao>() with singleton { instance<AppDatabase>().photosDao() }
        bind<TodosDao>() with singleton { instance<AppDatabase>().todosDao() }
        bind<CommentsDao>() with singleton { instance<AppDatabase>().commentsDao() }
        bind<ApiService>() with singleton { ApiService(instance()) }
        bind<ApiDataSource>() with singleton { ApiDataSourceImpl(instance()) }
        bind<AppRepository>() with singleton { AppRepositoryImpl(instance(), instance(), instance(), instance(), instance(), instance(), instance()) }
        bind<UsersViewModelFactory>() with provider { UsersViewModelFactory(instance()) }
        bind<AlbumsViewModelFactory>() with provider { AlbumsViewModelFactory(instance()) }
        bind<PostsViewModelFactory>() with provider { PostsViewModelFactory(instance()) }
        bind<PhotoListViewModelFactory>() with provider { PhotoListViewModelFactory(instance()) }
        bind<PhotoViewModelFactory>() with provider { PhotoViewModelFactory(instance()) }
        bind<CommentsViewHolderFactory>() with provider { CommentsViewHolderFactory(instance()) }
        bind<TodoViewModelFactory>() with provider { TodoViewModelFactory(instance()) }
        bind<CreatePostViewModelFactory>() with provider { CreatePostViewModelFactory(instance()) }
        bind<CreateCommentViewModelFactory>() with provider { CreateCommentViewModelFactory(instance()) }
    }

}