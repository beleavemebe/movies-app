package com.github.beleavemebe.moviesapp.ui.movies.recycler.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.github.beleavemebe.moviesapp.databinding.ListItemErrorBinding
import com.github.beleavemebe.moviesapp.databinding.ListItemLoaderBinding

class MovieReviewLoadStateAdapter(
    private val onRetryClicked: () -> Unit = {},
) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun getStateViewType(
        loadState: LoadState
    ): Int = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        is LoadState.Loading -> LOADER
        is LoadState.Error -> ERROR
    }

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder = when (loadState) {
        is LoadState.NotLoading -> error("Not supported")
        is LoadState.Loading -> createLoaderViewHolder(parent)
        is LoadState.Error -> createErrorViewHolder(parent, onRetryClicked)
    }

    private fun createLoaderViewHolder(
        parent: ViewGroup
    ) = LoaderViewHolder(
        ListItemLoaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    private fun createErrorViewHolder(
        parent: ViewGroup,
        onRetryClicked: () -> Unit
    ) = ErrorViewHolder(
        ListItemErrorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onRetryClicked
    )

    companion object {
        private const val LOADER = 0
        private const val ERROR = 1
    }
}
