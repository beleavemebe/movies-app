package com.github.beleavemebe.moviesapp.ui.movies.recycler

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.github.beleavemebe.moviesapp.databinding.ListItemMovieReviewBinding
import com.github.beleavemebe.moviesapp.model.MovieReview

class MovieReviewViewHolder(
    private val binding: ListItemMovieReviewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movieReview: MovieReview?) {
        if (movieReview != null) {
            renderItem(movieReview)
        } else {
            renderPlaceholder()
        }
    }

    private fun renderItem(movieReview: MovieReview) {
        binding.tvTitle.text = movieReview.headline
        binding.tvSummaryShort.text = movieReview.summaryShort
        binding.tvPublicationDate.text = movieReview.publicationDate
    }

    private fun renderPlaceholder() {
    }
}
