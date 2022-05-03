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
            openReview(movieReview.link.url)
        }

        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.isVisible = false
    }

    private fun openReview(reviewUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(reviewUrl))
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        if (intent.resolveActivity(itemView.context.packageManager) != null) {
            itemView.context.startActivity(intent)
        } else {
            Toast.makeText(
                itemView.context,
                R.string.unable_to_open_the_link,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun renderPlaceholder() {
        binding.shimmerLayout.isVisible = true
        binding.shimmerLayout.startShimmer()
    }
}
