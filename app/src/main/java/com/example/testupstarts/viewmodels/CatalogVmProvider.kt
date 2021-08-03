package com.example.testupstarts.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.CatalogInteractor

class CatalogVmProvider(private val interactor: CatalogInteractor): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatalogViewModel(interactor) as T
    }

}