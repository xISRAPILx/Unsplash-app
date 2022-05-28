package com.example.testupstarts.di

import com.example.testupstarts.repository.PrefsRepository
import com.example.testupstarts.repository.TokenRepository
import com.example.testupstarts.repository.room.PhotoDao
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import com.example.testupstarts.ui.photo_list_screen.PhotoInteractor
import com.example.testupstarts.repository.PhotoRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideAuthInteractor(
        prefsRepository: PrefsRepository,
        tokenRepository: TokenRepository
    ): AuthInteractor {
        return AuthInteractor(prefsRepository, tokenRepository)
    }

    @Provides
    fun providePhotoInteractor(
        photoRepository: PhotoRepository,
        photoDao: PhotoDao,
        prefsRepository: PrefsRepository
    ): PhotoInteractor {
        return PhotoInteractor(photoRepository, photoDao, prefsRepository)
    }
}
