package com.example.testupstarts

import android.util.Log
import com.example.testupstarts.repository.CatalogRepository
import com.example.testupstarts.repository.Photos
import com.example.testupstarts.repository.PhotosItem
import com.example.testupstarts.repository.PrefsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogInteractor(
    private val catalogRepo: CatalogRepository,
    private val prefsManager: PrefsManager
) {

    suspend fun getPhotosItems() = withContext(Dispatchers.IO) {
        val photos: List<Photos> = catalogRepo.getPhotos()
        val photosItems = mutableListOf<PhotosItem>()
        for (j in photos) {
            val photosItem =
                PhotosItem(j.id, j.desc,j.imageUrls.regular,j.imageUrls.small, j.author.username,  j.likes, j.author.name, j.author.instagramUsername, prefsManager.isFavorite(j.id))
            photosItems.add(photosItem)
        }
        Log.e("PHOTOITEMS", photosItems.toString())
        photosItems
    }

    fun addToFavorite(id: String) {
        prefsManager.addToFavorite(id)
    }

    fun deleteFromFavorite(id: String) {
        prefsManager.deleteFromFavorite(id)
    }
}