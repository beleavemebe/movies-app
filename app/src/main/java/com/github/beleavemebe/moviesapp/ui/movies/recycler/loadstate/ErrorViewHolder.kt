package com.github.beleavemebe.moviesapp.ui.movies.recycler.loadstate

import androidx.paging.LoadState
import com.github.beleavemebe.moviesapp.databinding.ListItemErrorBinding

class ErrorViewHolder(
    private val binding: ListItemErrorBinding,
    private val onRetryClicked: () -> Unit,
) : LoadStateViewHolder(binding.root) {
    override fun bind(loadState: LoadState) {
        require(loadState is LoadState.Error)
        binding.errorMessage.text = loadState.error.localizedMessage
        binding.root.setOnClickListener { onRetryClicked() }
    }
}
