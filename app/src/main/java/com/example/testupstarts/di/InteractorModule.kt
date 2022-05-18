package com.example.testupstarts.di

import com.example.testupstarts.ui.auth_screen.AuthInteractor
import com.example.testupstarts.ui.photo_list_screen.PhotoInteractor
import com.example.testupstarts.repository.PrefsRepository
import com.example.testupstarts.ui.photo_list_screen.repo.PhotoRepository
import com.example.testupstarts.repository.room.PhotoDao
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideAuthInteractor(prefsRepository: PrefsRepository): AuthInteractor {
        return AuthInteractor(prefsRepository)
    }

    @Provides
    fun providePhotoInteractor(
        photoRepository: PhotoRepository,
        photoDao: PhotoDao
    ): PhotoInteractor {
        return PhotoInteractor(photoRepository, photoDao)
    }
}
