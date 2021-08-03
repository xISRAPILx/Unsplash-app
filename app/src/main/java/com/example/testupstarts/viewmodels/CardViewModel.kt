package com.example.testupstarts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testupstarts.CatalogInteractor
import com.example.testupstarts.R
import com.example.testupstarts.SingleLiveEvent

class CardViewModel(private val interactor: CatalogInteractor) : ViewModel() {

    private var id: String? = null
    val snackbar: SingleLiveEvent<Int> = SingleLiveEvent()
    private val mutableFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val favorite: LiveData<Boolean> get() = mutableFavorite

    fun onFavClicked(favorite: Boolean) {
        id?.let {
            if (favorite) {
                interactor.addToFavorite(it)
                snackbar.postValue(R.string.snackbar_add_text)
            } else {
                interactor.deleteFromFavorite(it)
                snackbar.postValue(R.string.snackbar_delete_text)
            }
            mutableFavorite.postValue(favorite)
        }
    }

    fun onViewCreated(id: String, favorite: Boolean) {
        this.id = id
        mutableFavorite.postValue(favorite)
    }
}