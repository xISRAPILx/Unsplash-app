package com.example.testupstarts.repository

import retrofit2.http.*

interface ApiPhoto {
    @GET("topics/{id_or_slug}/photos")
    suspend fun getPhotos(
        @Path("id_or_slug") id: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("orientation") orientation: String
    ): List<Photo>

    @POST("photos/{id}/like")
    suspend fun likeAPhoto(
        @Path("id") idPhoto: String
    )

    @DELETE("photos/{id}/like")
    suspend fun unlikeAPhoto(
        @Path("id") idPhoto: String
    )
}