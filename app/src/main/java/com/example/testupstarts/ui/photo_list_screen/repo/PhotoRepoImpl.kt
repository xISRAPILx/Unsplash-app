package com.example.testupstarts.ui.photo_list_screen.repo

import com.example.testupstarts.BuildConfig
import com.example.testupstarts.repository.network.ApiPhoto
import com.example.testupstarts.repository.Photo

class PhotoRepoImpl(private val api: ApiPhoto) : PhotoRepository {
    override suspend fun getPhotos(): List<Photo> {
        return api.getPhotos(BuildConfig.ID_OR_SLUG,1,30,"portrait")
    }

    override suspend fun likeAPhoto(idPhoto: String) {
        api.likeAPhoto(idPhoto)
    }

    override suspend fun unlikeAPhoto(idPhoto: String) {
        api.unlikeAPhoto(idPhoto)
    }
}