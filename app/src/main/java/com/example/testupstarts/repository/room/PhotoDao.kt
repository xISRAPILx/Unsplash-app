package com.example.testupstarts.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.testupstarts.repository.models.PhotoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Insert
    suspend fun addToPhotoCache(photoList: List<PhotoItem?>)

    @Query("DELETE FROM photoCache")
    suspend fun deleteAll()

    @Transaction
    suspend fun clearAndAdd(photoList: List<PhotoItem?>){
        deleteAll()
        addToPhotoCache(photoList)
    }

    @Query("DELETE FROM photoCache WHERE id = :id")
    suspend fun deleteFromPhotoCache(id: String)

    @Query("UPDATE photoCache SET favorite = :favorite WHERE id= :id")
    suspend fun updatePhotoFromPhotoCache(id: String, favorite: Boolean)

    @Query("SELECT * FROM photoCache WHERE favorite = :favorite")
    fun getAllFavorite(favorite:Boolean): Flow<List<PhotoItem>>

    @Query("SELECT * FROM photoCache")
    fun getAllPhoto(): Flow<List<PhotoItem>>

}