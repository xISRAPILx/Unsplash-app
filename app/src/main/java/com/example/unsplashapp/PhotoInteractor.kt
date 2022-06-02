package com.example.unsplashapp

import com.example.unsplashapp.repository.PhotoDao
import com.example.unsplashapp.repository.PhotoRepository
import com.example.unsplashapp.repository.PhotosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoInteractor(
    private val photoRepo: PhotoRepository,
    private val photoDao: PhotoDao
) {
    // Network
    suspend fun getPhotosFromUnsplash() = withContext(Dispatchers.Main) {
        photoRepo.getPhotos().map { PhotosItem(it.id,
            it.desc,
            it.imageUrls.regular,
            it.imageUrls.small,
            it.author.username,
            it.likes,
            it.author.name,
            it.author.instagramUsername,
            it.liked_by_user) }
    }

    suspend fun likeAPhoto(idPhoto: String) {
        photoRepo.likeAPhoto(idPhoto)
    }

    suspend fun unlikeAPhoto(idPhoto: String) {
        photoRepo.unlikeAPhoto(idPhoto)
    }

    // DAO
    suspend fun getPhotosFromCache() = withContext(Dispatchers.Main){
        val cache = photoDao.getAllPhoto()
        cache
    }

    suspend fun clearAndAddToCache(photolist: List<PhotosItem>) {
        photoDao.clearAndAdd(photolist)
    }

}