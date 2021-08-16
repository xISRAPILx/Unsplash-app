package com.example.testupstarts.repository

import androidx.room.*

@Dao
interface PhotoDao {

    @Insert
    suspend fun addToCache(photoList: List<PhotosItem>)

    @Delete
    suspend fun deleteAll(photoList: List<PhotosItem>)

    @Transaction
    suspend fun clearAndAdd(photoList: List<PhotosItem>){
        deleteAll(photoList)
        addToCache(photoList)
    }

    @Query("DELETE FROM cache WHERE id = :id")
    suspend fun deleteFromCache(id: String)

    @Update
    suspend fun updatePhotoFromCache(photo: PhotosItem)

    //@Query("SELECT EXISTS (SELECT 1 FROM cache WHERE favorite = :favorite)")
    //suspend fun isFavorite(favorite:Boolean): Boolean

    @Query("SELECT * FROM cache")
    suspend fun getAllPhoto(): List<PhotosItem>

}