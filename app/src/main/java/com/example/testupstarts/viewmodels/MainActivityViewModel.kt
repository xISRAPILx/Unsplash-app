package com.example.testupstarts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testupstarts.interactors.AuthInteractor

class MainActivityViewModel(private val authInteractor: AuthInteractor): ViewModel() {

    private val mutableLoginResult : MutableLiveData<Boolean> = MutableLiveData()
    val loginResult : LiveData<Boolean> get() = mutableLoginResult

    fun onCreate() {
        mutableLoginResult.postValue(authInteractor.isGuest())
    }
}