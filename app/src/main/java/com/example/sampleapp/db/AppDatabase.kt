package com.example.sampleapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.sampleapp.db.dao.PhotosDao
import com.example.sampleapp.models.PhotosResponseModel
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [
        PhotosResponseModel::class
    ],
    version = VERSION_NUMBER, exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photosDao(): PhotosDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(
            context: Context,
            encryptedSharedPrefsClass: EncryptedSharedPrefsClass
        ): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, encryptedSharedPrefsClass).also { instance = it }
            }
        }

        private fun buildDatabase(
            context: Context,
            encryptedSharedPrefsClass: EncryptedSharedPrefsClass
        ): AppDatabase {
            val factory = SupportFactory(
                SQLiteDatabase.getBytes(
                    encryptedSharedPrefsClass.getDBEncryptionPhrase().toCharArray()
                )
            )
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).openHelperFactory(factory)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}