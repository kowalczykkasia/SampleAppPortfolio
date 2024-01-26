package com.example.sampleapp.home

import com.example.sampleapp.models.PhotosResponseModel
import com.example.sampleapp.repos.PhotosRepository
import com.example.sampleapp.utils.BaseViewModel
import com.example.sampleapp.utils.mutableSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PhotosRepository
) : BaseViewModel() {

    private val _responseList = mutableSharedFlow<PhotosResponseModel?>()
    val responseList: SharedFlow<PhotosResponseModel?>
        get() = _responseList

    init {
        launch {
            repository.getPhotos().collect {
                _responseList.emit(it.firstOrNull()?.let {
                    it.items = it.items.sortedBy { it.dateTaken }
                    it
                })
            }
        }
    }
}