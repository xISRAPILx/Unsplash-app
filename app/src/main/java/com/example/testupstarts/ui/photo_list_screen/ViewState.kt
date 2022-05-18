package com.example.testupstarts.ui

import com.example.testupstarts.repository.models.PhotosItem

sealed class ViewState
object ProgressState: ViewState()
object ErrorState : ViewState()
class ResultState(val list: List<PhotosItem>): ViewState()