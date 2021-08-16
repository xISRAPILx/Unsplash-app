package com.example.testupstarts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testupstarts.PhotoInteractor
import com.example.testupstarts.R
import com.example.testupstarts.SingleLiveEvent
import com.example.testupstarts.repository.PhotosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardViewModel(private val interactor: PhotoInteractor) : ViewModel() {

    private var id: String? = null
    val snackbar: SingleLiveEvent<Int> = SingleLiveEvent()
    private val mutableFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val favorite: LiveData<Boolean> get() = mutableFavorite

    fun onViewCreated(id: String, favorite: Boolean) {
        this.id = id
        mutableFavorite.postValue(favorite)
    }

    fun onFavClicked(favorite: Boolean, photo: PhotosItem?) = viewModelScope.launch {
            withContext(Dispatchers.Main) {
                id?.let {
                    if (favorite) {
                        if (photo != null) {
                            interactor.likeAPhoto(photo.id)
                        }
                        snackbar.postValue(R.string.snackbar_add_text)
                    } else {
                        if (photo != null) {
                            interactor.unlikeAPhoto(photo.id)
                        }
                        snackbar.postValue(R.string.snackbar_delete_text)
                    }
                    mutableFavorite.postValue(favorite)
                }
            }
    }
}