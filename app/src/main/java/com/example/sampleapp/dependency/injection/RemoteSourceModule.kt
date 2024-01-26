package com.example.sampleapp.dependency.injection

import com.example.sampleapp.BuildConfig
import com.example.sampleapp.remote.Service
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ) = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) = retrofit.create(Service::class.java)
}