package com.github.beleavemebe.moviesapp.ui.movies.recycler.loadstate

import androidx.paging.LoadState
import com.github.beleavemebe.moviesapp.databinding.ListItemLoaderBinding

class LoaderViewHolder(
    binding: ListItemLoaderBinding,
) : LoadStateViewHolder(binding.root) {
    override fun bind(loadState: LoadState) {
        require(loadState is LoadState.Loading)
    }
}
