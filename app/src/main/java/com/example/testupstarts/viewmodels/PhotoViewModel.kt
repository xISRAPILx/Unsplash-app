package com.example.testupstarts.viewmodels

import android.util.Log
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

    fun onCreate() {
        loadListFromNetwork()
        Log.e("onCreate()PhotoFragment", true.toString())
    }

    fun onViewCreated() {
        val flag = authInteractor.isGuest()
        mutableTokenFlag.postValue(flag)
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
                    if (cache != null) {
                        Log.e("postValue(ResultState)", true.toString())
                        mutableState.postValue(ResultState(cache))
                    }
                } catch (e: Exception) {
                    mutableState.postValue(ErrorState)
                }
            }
        }
    }

    private fun loadListFromNetwork() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val cache = photoInteractor.getPhotosFromCache()
                Log.e("cache", cache.toString())
                val list = photoInteractor.getPhotosFromUnsplash()
                Log.e("list", list.toString())

                for (l in list) {
                    for (c in cache.orEmpty()) {
                        if (l.id == c.id) {
                            Log.e("updatePhoto in Room", l.id.equals(c.id).toString())
                            photoInteractor.updatePhotoFromCache(l)
                        }
                        else {
                            Log.e("addPhoto in Room", l.id.equals(c.id).toString())
                            Log.e("l.id", l.id.toString())
                            Log.e("c.id", c.id.toString())
                            photoInteractor.addPhotoToCache(l)
                        }
                    }
                }
            }
        }
    }

    fun onFavClicked(favorite: Boolean, photo: PhotosItem) = viewModelScope.launch {
        if (favorite) {
            photoInteractor.likeAPhoto(photo.id)
            photoInteractor.updateFavorite(photo.id, true)
            snackbar.postValue(R.string.snackbar_add_text)
        } else {
            photoInteractor.unlikeAPhoto(photo.id)
            photoInteractor.updateFavorite(photo.id, false)
            snackbar.postValue(R.string.snackbar_delete_text)
        }
    }
}