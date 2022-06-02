package com.example.unsplashapp.di

import android.content.Context
import com.example.unsplashapp.AuthInteractor
import com.example.unsplashapp.PhotoInteractor
import com.example.unsplashapp.repository.*
import com.example.unsplashapp.viewmodels.AuthUnsplashVmProvider
import com.example.unsplashapp.viewmodels.CardVmProvider
import com.example.unsplashapp.viewmodels.MainActivityVmProvider
import com.example.unsplashapp.viewmodels.PhotoVmProvider

class AppContainer(private val context: Context) {

    private val retroModuleAuth = RetrofitModuleAuth()
    private val localRepo : LocalRepository = LocalRepoImpl(context, retroModuleAuth.apiClient)
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