package com.example.testupstarts.ui.auth_screen

import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedInject

class AuthViewModelFactory @AssistedInject constructor(
    private val authInteractor: AuthInteractor
): ViewModelProvider.Factory {
}