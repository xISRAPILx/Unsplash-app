package com.example.testupstarts.ui.photo_list_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testupstarts.R
import com.example.testupstarts.SingleLiveEvent
import com.example.testupstarts.repository.models.PhotosItem
import com.example.testupstarts.ui.ErrorState
import com.example.testupstarts.ui.ProgressState
import com.example.testupstarts.ui.ResultState
import com.example.testupstarts.ui.ViewState
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import kotlinx.coroutines.Dispatchers
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

    fun onCreate() {
        loadListFromNetwork()
    }

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
            withContext(Dispatchers.IO) {
                try {
                    val cache = photoInteractor.getPhotosFromCache()
                    Log.e("list", cache.size.toString())
                    mutableState.postValue(ResultState(cache))
                } catch (e: Exception) {
                    mutableState.postValue(ErrorState)
                }
            }
        }
    }

    private fun loadListFromNetwork() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val photoList = photoInteractor.getPhotosFromUnsplash()
                photoInteractor.clearAndAddToCache(photoList)
            }
        }
    }

    fun onFavClicked(favorite: Boolean, photo: PhotosItem) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            if (favorite) {
                photoInteractor.likeAPhoto(photo.id)
                photoInteractor.updatePhoto(photo.id, favorite)
                snackbar.postValue(R.string.snackbar_add_text)
            } else {
                photoInteractor.unlikeAPhoto(photo.id)
                photoInteractor.updatePhoto(photo.id, favorite)
                snackbar.postValue(R.string.snackbar_delete_text)
            }
        }
    }
}