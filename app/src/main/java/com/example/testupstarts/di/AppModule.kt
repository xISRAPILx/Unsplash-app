package com.example.testupstarts.di

import android.content.Context
import android.content.SharedPreferences
import com.example.testupstarts.AuthInteractor
import com.example.testupstarts.BuildConfig
import com.example.testupstarts.Interceptor
import com.example.testupstarts.repository.*
import com.example.testupstarts.repository.room.PhotoDao
import com.example.testupstarts.repository.room.PhotoDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideInterceptor(authInteractor: AuthInteractor): Interceptor {
        return Interceptor(authInteractor)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(interceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiAuth(retrofit: Retrofit): ApiAuth {
        return retrofit.create(ApiAuth::class.java)
    }

    @Provides
    @Singleton
    fun provideApiPhoto(retrofit: Retrofit): ApiPhoto {
        return retrofit.create(ApiPhoto::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(sharedPreferences: SharedPreferences, apiAuth: ApiAuth): LocalRepository {
        return LocalRepoImpl(sharedPreferences,apiAuth)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(apiPhoto: ApiPhoto): PhotoRepository {
        return PhotoRepoImpl(apiPhoto)
    }

    @Provides
    @Singleton
    fun providePhotoDao(context: Context): PhotoDao {
        return PhotoDatabase.getDatabase(context).photoDao()
    }
}