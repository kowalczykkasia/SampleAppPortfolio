package com.example.sampleapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sampleapp.models.PhotosResponseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos_table")
    fun getAllPhotos() : Flow<List<PhotosResponseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotosResponseModel(newDocument: PhotosResponseModel)

    @Query("DELETE FROM photos_table")
    fun deleteAll()

}