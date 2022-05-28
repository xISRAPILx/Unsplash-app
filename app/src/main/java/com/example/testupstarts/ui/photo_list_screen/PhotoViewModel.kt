package com.example.testupstarts.ui.photo_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testupstarts.R
import com.example.testupstarts.SingleLiveEvent
import com.example.testupstarts.repository.models.PhotoItem
import com.example.testupstarts.ui.ErrorState
import com.example.testupstarts.ui.ProgressState
import com.example.testupstarts.ui.ResultState
import com.example.testupstarts.ui.ViewState
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val photoInteractor: PhotoInteractor
) : ViewModel() {

    val snackbar: SingleLiveEvent<Int> = SingleLiveEvent()
    private val mutableState: MutableLiveData<ViewState> = MutableLiveData()
    val state: LiveData<ViewState> get() = mutableState
    private val mutableTokenFlag: MutableLiveData<Boolean> = MutableLiveData(false)
    val tokenFlag: LiveData<Boolean> get() = mutableTokenFlag

    //todo прикрутить котлин Flow
    init {
        loadList()
    }

    fun onViewCreated() {
        val flag = authInteractor.isGuest()
        mutableTokenFlag.postValue(flag)
    }

    fun onPhotoListUpdated() {
        loadList()
    }

    fun onFavClicked(favorite: Boolean, photo: PhotoItem) = viewModelScope.launch {
        if (favorite) {
            snackbar.postValue(R.string.snackbar_add_text)
        } else {
            snackbar.postValue(R.string.snackbar_delete_text)
        }
        photoInteractor.updatePhoto(photo.id, favorite)
    }

    private fun loadList() {
        mutableState.postValue(ProgressState)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                photoInteractor.updatedPhotos
                    .catch { mutableState.postValue(ErrorState) }
                    .collect { photos ->
                        mutableState.postValue(ResultState(photos))
                    }
            }
            photoInteractor.updatePhotoCache()
        }
    }
}