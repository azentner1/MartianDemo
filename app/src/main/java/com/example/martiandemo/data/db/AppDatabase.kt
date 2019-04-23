package com.example.martiandemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.martiandemo.data.db.dao.*
import com.example.martiandemo.data.db.models.*

@Database (
    entities = [User::class, Album::class, Post::class, Photo::class, Todo::class, Comment::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
    abstract fun albumsDao(): AlbumsDao
    abstract fun postsDao(): PostsDao
    abstract fun photosDao(): PhotoDao
    abstract fun todosDao(): TodosDao
    abstract fun commentsDao(): CommentsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: initDatabase(context).also {
                instance = it
            }

        }
        private fun initDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database.db").build()
    }
}