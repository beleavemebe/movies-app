package com.github.beleavemebe.moviesapp.ui.movies.recycler

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.beleavemebe.moviesapp.R
import com.github.beleavemebe.moviesapp.databinding.ListItemMovieReviewBinding
import com.github.beleavemebe.moviesapp.model.MovieReview

class MovieReviewViewHolder(
    private val onReviewClicked: (MovieReview) -> Unit,
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
        Glide.with(binding.root)
            .load(movieReview.multimedia.src)
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(binding.ivImage)

        binding.root.setOnClickListener {
            onReviewClicked(movieReview)
        }

        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.isVisible = false
    }

    private fun renderPlaceholder() {
        binding.tvTitle.text = ""
        binding.tvSummaryShort.text = ""
        binding.tvPublicationDate.text = ""
        binding.ivImage.setImageDrawable(null)
        binding.root.setOnClickListener {}

        binding.shimmerLayout.isVisible = true
        binding.shimmerLayout.startShimmer()
    }
}
