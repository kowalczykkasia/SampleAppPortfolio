package com.example.sampleapp.remote

import com.example.sampleapp.models.PhotosResponseModel
import com.example.sampleapp.utils.CAT
import com.example.sampleapp.utils.JSON
import com.example.sampleapp.utils.NO_JSON_CALLBACK
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("photos_public.gne")
    suspend fun getPhotos(
        @Query("format") format: String = JSON,
        @Query("tags") tags: String = CAT,
        @Query("nojsoncallback") noJsonCallback: Int = NO_JSON_CALLBACK,
    ): PhotosResponseModel
}