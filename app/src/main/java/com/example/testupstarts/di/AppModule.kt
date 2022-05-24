package com.example.testupstarts.di

import android.content.Context
import android.content.SharedPreferences
import com.example.testupstarts.BuildConfig
import com.example.testupstarts.BuildConfig.BASE_URL
import com.example.testupstarts.repository.PrefsRepoImpl
import com.example.testupstarts.repository.PrefsRepository
import com.example.testupstarts.repository.TokenRepoImpl
import com.example.testupstarts.repository.TokenRepository
import com.example.testupstarts.repository.network.ApiPhoto
import com.example.testupstarts.repository.network.AuthInterceptor
import com.example.testupstarts.repository.room.PhotoDao
import com.example.testupstarts.repository.room.PhotoDatabase
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import com.example.testupstarts.repository.PhotoRepoImpl
import com.example.testupstarts.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideAuthInterceptor(authInteractor: AuthInteractor): AuthInterceptor {
        return AuthInterceptor(authInteractor)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: AuthInterceptor
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
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
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
    fun providePrefsRepository(sharedPreferences: SharedPreferences): PrefsRepository {
        return PrefsRepoImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun providePhotoRepository(apiPhoto: ApiPhoto): PhotoRepository {
        return PhotoRepoImpl(apiPhoto)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(apiPhoto: ApiPhoto): TokenRepository {
        return TokenRepoImpl(apiPhoto)
    }

    @Provides
    @Singleton
    fun providePhotoDao(context: Context): PhotoDao {
        return PhotoDatabase.getDatabase(context).photoDao()
    }
}