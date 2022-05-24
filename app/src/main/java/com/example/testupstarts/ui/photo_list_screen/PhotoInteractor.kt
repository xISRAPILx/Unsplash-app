package com.example.testupstarts.ui.photo_list_screen

import com.example.testupstarts.repository.PhotoRepository
import com.example.testupstarts.repository.PrefsRepository
import com.example.testupstarts.repository.models.PhotosItem
import com.example.testupstarts.repository.room.PhotoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoInteractor @Inject constructor(
    private val photoRepo: PhotoRepository,
    private val photoDao: PhotoDao,
    private val prefsRepo: PrefsRepository
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

    suspend fun updatePhoto(idPhoto: String, fav: Boolean) {
        photoRepo.setLike(idPhoto, fav)
        updatePhotoInDao(idPhoto, fav)
    }

    // DAO
    suspend fun getPhotosFromCache() = photoDao.getAllPhoto()

    suspend fun clearAndAddToCache(photoList: List<PhotosItem?>) {
        photoDao.clearAndAdd(photoList)
    }

    private suspend fun updatePhotoInDao(id: String, favorite: Boolean) {
        photoDao.updatePhotoFromCache(id, favorite)
    }

    fun isGuest() = prefsRepo.isGuest()
}