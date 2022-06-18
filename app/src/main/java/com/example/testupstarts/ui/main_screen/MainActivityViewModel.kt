package com.example.testupstarts.ui.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val authInteractor: AuthInteractor): ViewModel() {

    private val mutableLoginResult : MutableLiveData<Boolean> = MutableLiveData()
    val loginResult : LiveData<Boolean> get() = mutableLoginResult

    fun onCreate() {
        mutableLoginResult.postValue(authInteractor.isLogged())
    }
}