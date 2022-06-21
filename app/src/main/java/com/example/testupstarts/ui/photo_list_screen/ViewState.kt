package com.example.testupstarts.ui.photo_list_screen

import com.example.testupstarts.repository.models.PhotoItem

sealed class ViewState

object ProgressState : ViewState()

object ErrorState : ViewState()

data class ResultState(
    val photos: List<PhotoItem>,
    val isLogged: Boolean
) : ViewState()
