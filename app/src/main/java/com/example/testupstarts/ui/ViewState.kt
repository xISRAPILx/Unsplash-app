package com.example.testupstarts.ui

import com.example.testupstarts.repository.PhotosItem

sealed class ViewState
object ProgressState: ViewState()
object ErrorState : ViewState()
class ListState(val list: List<PhotosItem>): ViewState()