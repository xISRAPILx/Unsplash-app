package com.example.testupstarts.repository

import com.example.testupstarts.BuildConfig

class CatalogRepoImpl(private val api: Api) : CatalogRepository {
    override suspend fun getPhotos(): List<Photos> {
        return api.getPhotos(BuildConfig.ID_OR_SLUG,1,30,"portrait")
    }
}