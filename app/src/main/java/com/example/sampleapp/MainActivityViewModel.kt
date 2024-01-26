package com.example.sampleapp

import com.example.sampleapp.repos.PhotosRepository
import com.example.sampleapp.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: PhotosRepository
) : BaseViewModel() {

    init {
        launch {
            repository.updateData()
        }
    }

}