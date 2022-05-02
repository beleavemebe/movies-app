package com.github.beleavemebe.moviesapp.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.beleavemebe.moviesapp.R
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.observe(viewLifecycleOwner, null, ::handleSideEffect)
    }

    private fun handleSideEffect(sideEffect: SplashSideEffect) = when (sideEffect) {
        SplashSideEffect.NavigateToMovieList -> navigateToMovieListFragment()
    }

    private fun navigateToMovieListFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_movieListFragment)
    }
}
