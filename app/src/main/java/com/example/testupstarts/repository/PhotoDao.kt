package com.example.testupstarts.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PhotoDao {

    @Insert
    suspend fun addToCache(photo: PhotosItem)

    @Query("DELETE FROM cache WHERE id = :id")
    suspend fun deleteFromCache(id: String)

    @Query("UPDATE cache SET favorite = :favorite WHERE id= :id")
    suspend fun updateFavoriteFromCache(id: String, favorite: Boolean)

    @Update
    suspend fun updatePhotoFromCache(photo: PhotosItem)

    @Query("SELECT EXISTS (SELECT 1 FROM cache WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean

    @Query("SELECT * FROM cache")
    suspend fun getAllPhoto(): List<PhotosItem>?

    @Query("SELECT * FROM cache WHERE favorite = :favorite")
    suspend fun getAllFavoritePhoto(favorite: Boolean): List<PhotosItem>?
}