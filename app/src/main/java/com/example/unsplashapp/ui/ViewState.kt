package com.example.unsplashapp.ui

import com.example.unsplashapp.repository.PhotosItem

sealed class ViewState
object ProgressState: ViewState()
object ErrorState : ViewState()
class ResultState(val list: List<PhotosItem>): ViewState()