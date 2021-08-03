package com.example.testupstarts.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testupstarts.CatalogInteractor
import com.example.testupstarts.R
import com.example.testupstarts.SingleLiveEvent
import com.example.testupstarts.ui.ErrorState
import com.example.testupstarts.ui.ListState
import com.example.testupstarts.ui.ProgressState
import com.example.testupstarts.ui.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogViewModel(private val interactor: CatalogInteractor) : ViewModel(){

    val snackbar: SingleLiveEvent<Int> = SingleLiveEvent()
    private val mutableState: MutableLiveData<ViewState> = MutableLiveData()
    val state: LiveData<ViewState> get() = mutableState

    fun onViewCreated() {
        loadList()
    }

    fun onTryAgainClicked() {
        loadList()
    }

    fun loadList() {
        mutableState.postValue(ProgressState)
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    val jeansList = interactor.getPhotosItems()
                    Log.e("loadList - jeansList", jeansList.toString())
                    mutableState.postValue(ListState(jeansList))
                } catch (e: Exception) {
                    Log.e("Exc",e.toString())
                    mutableState.postValue(ErrorState)
                }
            }
        }
    }

    fun onFavClicked(favorite:Boolean, id:String) {
                if (favorite) {
                    interactor.addToFavorite(id)
                    snackbar.postValue(R.string.snackbar_add_text)
                } else {
                    interactor.deleteFromFavorite(id)
                    snackbar.postValue(R.string.snackbar_delete_text)
                }
    }
}