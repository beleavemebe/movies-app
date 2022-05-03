package com.github.beleavemebe.moviesapp.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


fun <T> Flow<T>.repeatWhenStarted(lifecycle: Lifecycle) {
    val flow = this
    lifecycle.coroutineScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect()
        }
    }
}
