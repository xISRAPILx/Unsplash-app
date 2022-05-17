package com.example.testupstarts.di

import android.content.Context
import com.example.testupstarts.AuthInteractor
import com.example.testupstarts.PhotoInteractor
import com.example.testupstarts.repository.LocalRepoImpl
import com.example.testupstarts.repository.LocalRepository
import com.example.testupstarts.repository.PhotoRepoImpl
import com.example.testupstarts.repository.PhotoRepository
import com.example.testupstarts.repository.room.PhotoDatabase
import com.example.testupstarts.viewmodels.AuthUnsplashVmProvider
import com.example.testupstarts.viewmodels.CardVmProvider
import com.example.testupstarts.viewmodels.MainActivityVmProvider
import com.example.testupstarts.viewmodels.PhotoVmProvider

class AppContainer(private val context: Context) {

    private val retroModuleAuth = AuthRetrofitService()
    private val localRepo : LocalRepository = LocalRepoImpl(context, retroModuleAuth.apiClient)
    private val authInteractor = AuthInteractor(localRepo)
    private val retroModule = RetrofitService(authInteractor)
    private val localDataSource = PhotoDatabase.getDatabase(context).photoDao()
    private val catalogRepo : PhotoRepository = PhotoRepoImpl(retroModule.apiClient)
    private val photoInteractor = PhotoInteractor(catalogRepo, localDataSource)

    fun getPhotoVmProvider() = PhotoListVmProvider(authInteractor, photoInteractor)

    fun getCardVmProvider() = PhotoVmProvider(photoInteractor)

    fun getAuthUnsplashVmProvider() = AuthUnsplashVmProvider(authInteractor)

    fun getMainActivityVmProvider() = MainActivityVmProvider(authInteractor)
}