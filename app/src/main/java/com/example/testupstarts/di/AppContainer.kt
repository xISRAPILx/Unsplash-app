package com.example.testupstarts.di

import android.content.Context
import com.example.testupstarts.AuthInteractor
import com.example.testupstarts.PhotoInteractor
import com.example.testupstarts.repository.*
import com.example.testupstarts.viewmodels.AuthUnsplashVmProvider
import com.example.testupstarts.viewmodels.CardVmProvider
import com.example.testupstarts.viewmodels.MainActivityVmProvider
import com.example.testupstarts.viewmodels.PhotoVmProvider

class AppContainer(private val context: Context) {

    private val localRepo : LocalRepository = LocalRepoImpl(context)
    private val authInteractor = AuthInteractor(localRepo)
    private val retroModule = RetrofitModule(authInteractor)
    private val localDataSource = PhotoDatabase.getDatabase(context).photoDao()
    private val catalogRepo : PhotoRepository = PhotoRepoImpl(retroModule.apiClient)
    private val photoInteractor = PhotoInteractor(catalogRepo, localDataSource)

    fun getPhotoVmProvider() = PhotoVmProvider(authInteractor, photoInteractor)

    fun getCardVmProvider() = CardVmProvider(photoInteractor)

    fun getAuthUnsplashVmProvider() = AuthUnsplashVmProvider(authInteractor)

    fun getMainActivityVmProvider() = MainActivityVmProvider(authInteractor)
}