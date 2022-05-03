package com.github.beleavemebe.moviesapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel(), ContainerHost<SplashState, SplashSideEffect> {
    override val container: Container<SplashState, SplashSideEffect> =
        container(SplashState())

    init {
        startSplash()
    }

    private fun startSplash() = intent {
        delay(SPLASH_DURATION)
        postSideEffect(SplashSideEffect.NavigateToMovieList)
    }

    companion object {
        const val SPLASH_DURATION = 1000L
    }
}
