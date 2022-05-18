package com.example.testupstarts.di

import android.content.Context
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import com.example.testupstarts.ui.photo_list_screen.PhotoInteractor
import com.example.testupstarts.repository.PrefsRepoImpl
import com.example.testupstarts.repository.PrefsRepository
import com.example.testupstarts.ui.photo_list_screen.repo.PhotoRepoImpl
import com.example.testupstarts.ui.photo_list_screen.repo.PhotoRepository
import com.example.testupstarts.repository.room.PhotoDatabase
import com.example.testupstarts.ui.auth_screen.AuthUnsplashVmProvider
import com.example.testupstarts.ui.card_screen.CardVmProvider
import com.example.testupstarts.ui.main_screen.MainActivityVmProvider
import com.example.testupstarts.ui.photo_list_screen.PhotoVmProvider

class AppContainer(private val context: Context) {

    private val retroModuleAuth = AuthRetrofitService()
    private val prefsRepo : PrefsRepository = PrefsRepoImpl(context, retroModuleAuth.apiClient)
    private val authInteractor = AuthInteractor(prefsRepo)
    private val retroModule = RetrofitService(authInteractor)
    private val localDataSource = PhotoDatabase.getDatabase(context).photoDao()
    private val catalogRepo : PhotoRepository = PhotoRepoImpl(retroModule.apiClient)
    private val photoInteractor = PhotoInteractor(catalogRepo, localDataSource)

    fun getPhotoVmProvider() = PhotoListVmProvider(authInteractor, photoInteractor)

    fun getCardVmProvider() = PhotoVmProvider(photoInteractor)

    fun getAuthUnsplashVmProvider() = AuthUnsplashVmProvider(authInteractor)

    fun getMainActivityVmProvider() = MainActivityVmProvider(authInteractor)
}