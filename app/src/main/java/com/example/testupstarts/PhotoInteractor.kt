package com.example.testupstarts

import android.util.Log
import com.example.testupstarts.repository.PhotoDao
import com.example.testupstarts.repository.PhotoRepository
import com.example.testupstarts.repository.PhotosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoInteractor(
    private val photoRepo: PhotoRepository,
    private val photoDao: PhotoDao
) {
    // Network
    suspend fun getPhotosFromUnsplash() = withContext(Dispatchers.IO) {
        photoRepo.getPhotos().map { PhotosItem(it.id,
            it.desc,
            it.imageUrls.regular,
            it.imageUrls.small,
            it.author.username,
            it.likes,
            it.author.name,
            it.author.instagramUsername,
            photoDao.isFavorite(it.id)) }
    }

    suspend fun likeAPhoto(idPhoto: String) {
        photoRepo.likeAPhoto(idPhoto)
    }

    suspend fun unlikeAPhoto(idPhoto: String) {
        photoRepo.likeAPhoto(idPhoto)
    }

    // DAO
    suspend fun updatePhotoFromCache(photo: PhotosItem) {
        photoDao.updatePhotoFromCache(photo)
    }

    suspend fun getPhotosFromCache() = withContext(Dispatchers.IO){
        val cache = photoDao.getAllPhoto()
        cache
    }

    suspend fun addPhotoToCache(photo: PhotosItem) {
        Log.e("photoDao.addToCache", true.toString())
        photoDao.addToCache(photo)
    }

    suspend fun updateFavorite(id:String, favorite: Boolean) {
        photoDao.updateFavoriteFromCache(id, favorite)
    }

}