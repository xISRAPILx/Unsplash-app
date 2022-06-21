package com.example.testupstarts.ui

import com.example.testupstarts.repository.models.PhotoItem
import com.example.testupstarts.ui.photo_list_screen.PhotoUiState

sealed class ViewState
object ProgressState: ViewState()
object ErrorState : ViewState()
class ResultState(val photoUiState: PhotoUiState): ViewState()