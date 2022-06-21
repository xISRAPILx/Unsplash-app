package com.example.testupstarts.ui.photo_list_screen

import androidx.lifecycle.*
import com.example.testupstarts.R
import com.example.testupstarts.SingleLiveEvent
import com.example.testupstarts.repository.models.PhotoItem
import com.example.testupstarts.ui.ErrorState
import com.example.testupstarts.ui.ProgressState
import com.example.testupstarts.ui.ResultState
import com.example.testupstarts.ui.auth_screen.AuthInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class PhotoViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val photoInteractor: PhotoInteractor
) : ViewModel() {

    val snackbar: SingleLiveEvent<Int> = SingleLiveEvent()
    private val mutableTokenFlag: MutableLiveData<Boolean> = MutableLiveData(false)
    val tokenFlag: LiveData<Boolean> get() = mutableTokenFlag
/*    val postsLiveData:LiveData<ViewState> =  photoInteractor.updatedPhotos
        .map<List<PhotoItem>, ViewState> {ResultState(it) }
        .catch { emit(ErrorState)}
        .asLiveData(viewModelScope.coroutineContext)*/

    val photosLiveData = liveData {
        emit(ProgressState)
        try {
            val photos = photoInteractor.updatedPhotos
            val isLogged = authInteractor.isLogged()
            photos.collect{
                emit(PhotoUiState(
                    photos = it,
                    isLogged = isLogged
                ))
            }
        } catch (e:Exception) {
            emit(ErrorState)
        }
    }

    fun onViewCreated() {
        loadList()
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
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                photoInteractor.loadList()
            }
        }
    }
}