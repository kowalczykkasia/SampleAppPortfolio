package com.example.sampleapp.dependency.injection

import android.content.Context
import com.example.sampleapp.db.AppDatabase
import com.example.sampleapp.db.EncryptedSharedPrefsClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext context: Context,
        encryptedSharedPrefsClass: EncryptedSharedPrefsClass
    ) = AppDatabase.getInstance(context, encryptedSharedPrefsClass)

    @Provides
    @Singleton
    fun provideEncryptedSharedPrefs(@ApplicationContext context: Context) =
        EncryptedSharedPrefsClass(context)
}