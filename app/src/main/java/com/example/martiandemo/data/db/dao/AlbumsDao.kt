package com.example.martiandemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.martiandemo.data.db.models.Album

@Dao
interface AlbumsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(albums: List<Album>)

    @Query("SELECT * FROM albums")
    fun getAlbums(): LiveData<List<Album>>

}