package com.example.sampleapp.repos

import com.example.sampleapp.db.dao.PhotosDao
import com.example.sampleapp.remote.Service

class PhotosRepository(
    private val service: Service,
    private val dao: PhotosDao
) {

    fun getPhotos() = dao.getAllPhotos()

    suspend fun updateData() {
        val response = service.getPhotos()
        dao.deleteAll()
        dao.insertPhotosResponseModel(response)
    }
}