package com.example.martiandemo.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.martiandemo.data.db.models.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(photos: List<Photo>)

    @Query("SELECT * FROM photos")
    fun getPhotos() : LiveData<List<Photo>>

    @Query("SELECT * FROM photos WHERE albumId = :album_id")
    fun getPhoto(album_id: Int) : LiveData<List<Photo>>

}