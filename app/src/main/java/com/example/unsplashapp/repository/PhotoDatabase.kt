package com.example.unsplashapp.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhotosItem::class], version = 1)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: PhotoDatabase? = null

        fun getDatabase(context: Context): PhotoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhotoDatabase::class.java,
                    "cache"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}