package com.example.testupstarts.repository

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("topics/{id_or_slug}/photos")
    suspend fun getPhotos(
        @Path("id_or_slug") id: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("orientation") orientation: String
    ): List<Photos>
}