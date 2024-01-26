package com.example.sampleapp.utils

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> mutableSharedFlow() = MutableSharedFlow<T>(
    replay = 1,
    extraBufferCapacity = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST
)