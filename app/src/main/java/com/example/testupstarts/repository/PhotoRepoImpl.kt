package com.example.testupstarts.repository

import com.example.testupstarts.BuildConfig
import com.example.testupstarts.repository.models.Photo
import com.example.testupstarts.repository.network.ApiPhoto
import javax.inject.Inject

class PhotoRepoImpl @Inject constructor(private val api: ApiPhoto) : PhotoRepository {
    override suspend fun getPhotos(): List<Photo> {
        return api.getPhotos(BuildConfig.ID_OR_SLUG,1,30, PORTRAIT)
    }

    override suspend fun setLike(idPhoto: String, like: Boolean) {
        if(like)
            api.likeAPhoto(idPhoto)
        else
            api.unlikeAPhoto(idPhoto)
    }

    companion object {
        const val PORTRAIT = "portrait"
    }
}