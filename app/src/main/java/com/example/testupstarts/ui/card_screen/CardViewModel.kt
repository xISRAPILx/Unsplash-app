package com.example.testupstarts.ui.card_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testupstarts.R
import com.example.testupstarts.SingleLiveEvent
import com.example.testupstarts.repository.models.PhotoItem
import com.example.testupstarts.ui.photo_list_screen.PhotoInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardViewModel @Inject constructor(private val interactor: PhotoInteractor) : ViewModel() {

    private var id: String? = null
    val snackbar: SingleLiveEvent<Int> = SingleLiveEvent()
    private val mutableFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val favorite: LiveData<Boolean> get() = mutableFavorite
    val loggedUserValue: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun onCreate() {
        loggedUserValue.postValue(interactor.isLogged())
    }

    fun onViewCreated(id: String, favorite: Boolean) {
        this.id = id
        mutableFavorite.postValue(favorite)
    }

    fun onFavClicked(favorite: Boolean, photo: PhotoItem?) = viewModelScope.launch {
        id?.let {
            interactor.updatePhoto(it, favorite)
            if (favorite)
                snackbar.postValue(R.string.snackbar_add_text)
            else
                snackbar.postValue(R.string.snackbar_delete_text)
            mutableFavorite.postValue(favorite)
        }
    }
}