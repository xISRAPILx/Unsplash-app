package com.example.testupstarts.repository

interface CatalogRepository {
    suspend fun getPhotos(): List<Photos>

}