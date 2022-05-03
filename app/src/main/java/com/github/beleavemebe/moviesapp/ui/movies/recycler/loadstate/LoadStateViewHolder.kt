package com.github.beleavemebe.moviesapp.ui.movies.recycler.loadstate

import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

abstract class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(loadState: LoadState)
}

