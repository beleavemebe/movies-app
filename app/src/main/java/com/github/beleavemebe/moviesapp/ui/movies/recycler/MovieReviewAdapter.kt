package com.github.beleavemebe.moviesapp.ui.movies.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.beleavemebe.moviesapp.databinding.ListItemMovieReviewBinding
import com.github.beleavemebe.moviesapp.model.MovieReview

class MovieReviewAdapter(
    private val onReviewClicked: (MovieReview) -> Unit,
) : PagingDataAdapter<MovieReview, MovieReviewViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
        return MovieReviewViewHolder(
            onReviewClicked,
            ListItemMovieReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieReview>() {
            override fun areItemsTheSame(
                oldItem: MovieReview,
                newItem: MovieReview
            ): Boolean {
                return oldItem.link == newItem.link
            }

            override fun areContentsTheSame(
                oldItem: MovieReview,
                newItem: MovieReview
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
