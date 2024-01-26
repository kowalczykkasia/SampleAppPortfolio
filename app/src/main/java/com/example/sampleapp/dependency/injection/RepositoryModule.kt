package com.example.sampleapp.dependency.injection

import com.example.sampleapp.db.AppDatabase
import com.example.sampleapp.remote.Service
import com.example.sampleapp.repos.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePhotosRepository(service: Service, appDatabase: AppDatabase) = PhotosRepository(service, appDatabase.photosDao())
}