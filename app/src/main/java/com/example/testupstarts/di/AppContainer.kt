package com.example.testupstarts.di

import android.content.Context
import com.example.testupstarts.CatalogInteractor
import com.example.testupstarts.repository.CatalogRepoImpl
import com.example.testupstarts.repository.CatalogRepository
import com.example.testupstarts.repository.PrefsManager
import com.example.testupstarts.viewmodels.CardVmProvider
import com.example.testupstarts.viewmodels.CatalogVmProvider

class AppContainer(private val context: Context) {

    private val retroModule = RetrofitModule()
    private val prefsManager = PrefsManager(context)
    private val catalogRepo : CatalogRepository = CatalogRepoImpl(retroModule.apiClient)
    private val interactor = CatalogInteractor(catalogRepo, prefsManager)

    fun getCatalogVmProvider() = CatalogVmProvider(interactor)

    fun getCardVmProvider() = CardVmProvider(interactor)
}