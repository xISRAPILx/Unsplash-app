package com.example.testupstarts.ui.photo_list_screen

import com.example.testupstarts.ui.photo_list_screen.repo.PhotoRepository
import com.example.testupstarts.repository.models.PhotosItem
import com.example.testupstarts.repository.room.PhotoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoInteractor @Inject constructor(
    private val photoRepo: PhotoRepository,
    private val photoDao: PhotoDao
) {
    // Network
    suspend fun getPhotosFromUnsplash() = withContext(Dispatchers.IO) {
        photoRepo.getPhotos().map {
            it.id?.let { it1 ->
                PhotosItem(
                    it1,
                    it.desc,
                    it.imageUrls?.regular,
                    it.imageUrls?.small,
                    it.author?.username,
                    it.likes,
                    it.author?.name,
                    it.author?.instagramUsername,
                    it.liked_by_user)
            }
        }
    }

    suspend fun likePhoto(idPhoto: String) {
        photoRepo.likeAPhoto(idPhoto)
    }

    suspend fun unlikePhoto(idPhoto: String) {
        photoRepo.unlikeAPhoto(idPhoto)
    }

    // DAO
    suspend fun getPhotosFromCache() = withContext(Dispatchers.IO){
        val cache = photoDao.getAllPhoto()
        cache
    }

    suspend fun clearAndAddToCache(photoList: List<PhotosItem>) {
        photoDao.clearAndAdd(photoList)
    }

    suspend fun updatePhoto(id: String, favorite: Boolean) {
        photoDao.updatePhotoFromCache(id, favorite)
    }
}