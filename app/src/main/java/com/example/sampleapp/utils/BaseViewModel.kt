package com.example.sampleapp.utils

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope, LifecycleObserver {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val _error = mutableSharedFlow<Exception>()
    val error: SharedFlow<Exception>
        get() = _error

    override fun onCleared() {
        job.cancel()
    }

    fun <T> launch(action: suspend () -> T) {
        launch(Dispatchers.IO) {
            try {
                action.invoke()
            } catch (e: Exception) {
                _error.emit(e)
            }
        }
    }
}