package com.example.testupstarts.ui.photo_list_screen

import com.example.testupstarts.repository.models.PhotoItem

data class PhotoUiState(
    val photos: List<PhotoItem>,
    val isLogged: Boolean
)
