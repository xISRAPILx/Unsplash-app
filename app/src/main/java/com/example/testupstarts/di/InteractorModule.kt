package com.example.testupstarts.di

import com.example.testupstarts.AuthInteractor
import com.example.testupstarts.PhotoInteractor
import com.example.testupstarts.repository.LocalRepository
import com.example.testupstarts.repository.PhotoRepository
import com.example.testupstarts.repository.room.PhotoDao
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideAuthInteractor(localRepository: LocalRepository): AuthInteractor {
        return AuthInteractor(localRepository)
    }

    @Provides
    fun providePhotoInteractor(
        photoRepository: PhotoRepository,
        photoDao: PhotoDao
    ): PhotoInteractor {
        return PhotoInteractor(photoRepository, photoDao)
    }
}
