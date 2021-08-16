package com.example.testupstarts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testupstarts.AuthInteractor
import com.example.testupstarts.PhotoInteractor
import com.example.testupstarts.R
import com.example.testupstarts.SingleLiveEvent
import com.example.testupstarts.repository.PhotosItem
import com.example.testupstarts.ui.ErrorState
import com.example.testupstarts.ui.ProgressState
import com.example.testupstarts.ui.ResultState
import com.example.testupstarts.ui.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotoViewModel(
    private val authInteractor: AuthInteractor,
    private val photoInteractor: PhotoInteractor
) : ViewModel() {

    val snackbar: SingleLiveEvent<Int> = SingleLiveEvent()
    private val mutableState: MutableLiveData<ViewState> = MutableLiveData()
    val state: LiveData<ViewState> get() = mutableState
    private val mutableTokenFlag: MutableLiveData<Boolean> = MutableLiveData(false)
    val tokenFlag: LiveData<Boolean> get() = mutableTokenFlag
    private val mutableCache: MutableLiveData<List<PhotosItem>> = MutableLiveData()
    val cache: LiveData<List<PhotosItem>> get() = mutableCache

    fun onViewCreated() {
        val flag = authInteractor.isGuest()
        mutableTokenFlag.postValue(flag)
        loadListFromNetwork()
        loadListFromCache()
    }

    fun onTryAgainClicked() {
        loadListFromCache()
    }

    private fun loadListFromCache() {
        mutableState.postValue(ProgressState)
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    val cache = photoInteractor.getPhotosFromCache()
                    mutableCache.postValue(cache)
                    mutableState.postValue(ResultState(cache))
                } catch (e: Exception) {
                    mutableState.postValue(ErrorState)
                }
            }
        }
    }

    private fun loadListFromNetwork() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val photoList = photoInteractor.getPhotosFromUnsplash()
                photoInteractor.clearAndAddToCache(photoList)
            }
        }
    }

    fun onFavClicked(favorite: Boolean, photo: PhotosItem) = viewModelScope.launch {
        if (favorite) {
            photoInteractor.likeAPhoto(photo.id)
            snackbar.postValue(R.string.snackbar_add_text)
        } else {
            photoInteractor.unlikeAPhoto(photo.id)
            snackbar.postValue(R.string.snackbar_delete_text)
        }
    }
}