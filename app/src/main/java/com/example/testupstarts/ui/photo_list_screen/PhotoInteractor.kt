package com.example.testupstarts.ui.photo_list_screen

import com.example.testupstarts.ui.photo_list_screen.repo.PhotoRepository
import com.example.testupstarts.repository.PhotosItem
import com.example.testupstarts.repository.room.PhotoDao
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
            it.liked_by_user) }
    }

    suspend fun likeAPhoto(idPhoto: String) {
        photoRepo.likeAPhoto(idPhoto)
    }

    suspend fun unlikeAPhoto(idPhoto: String) {
        photoRepo.unlikeAPhoto(idPhoto)
    }

    // DAO
    suspend fun getPhotosFromCache() = withContext(Dispatchers.IO){
        val cache = photoDao.getAllPhoto()
        cache
    }

    suspend fun clearAndAddToCache(photolist: List<PhotosItem>) {
        photoDao.clearAndAdd(photolist)
    }

    suspend fun updatePhoto(id: String, favorite: Boolean) {
        photoDao.updatePhotoFromCache(id, favorite)
    }
}