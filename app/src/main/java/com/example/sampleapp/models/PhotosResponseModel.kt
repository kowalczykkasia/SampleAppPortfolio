package com.example.sampleapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos_table")
data class PhotosResponseModel(
    val description: String,
    val generator: String,
    var items: List<Item>,
    val link: String,
    val modified: String,
    val title: String,
    @PrimaryKey
    val timestamp: Long = System.currentTimeMillis()
)