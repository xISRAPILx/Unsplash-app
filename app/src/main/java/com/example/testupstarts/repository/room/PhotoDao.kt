package com.example.testupstarts.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.testupstarts.repository.PhotosItem

@Dao
interface PhotoDao {

    @Insert
    suspend fun addToCache(photoList: List<PhotosItem>)

    @Query("DELETE FROM cache")
    suspend fun deleteAll()

    @Transaction
    suspend fun clearAndAdd(photoList: List<PhotosItem>){
        deleteAll()
        addToCache(photoList)
    }

    @Query("DELETE FROM cache WHERE id = :id")
    suspend fun deleteFromCache(id: String)

    @Query("UPDATE cache SET favorite = :favorite WHERE id= :id")
    suspend fun updatePhotoFromCache(id: String, favorite: Boolean)

    @Query("SELECT * FROM cache WHERE favorite = :favorite")
    suspend fun getAllFavorite(favorite:Boolean): List<PhotosItem>

    @Query("SELECT * FROM cache")
    suspend fun getAllPhoto(): List<PhotosItem>

}