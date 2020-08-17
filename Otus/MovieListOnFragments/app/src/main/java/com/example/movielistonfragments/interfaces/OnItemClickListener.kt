package com.example.movielistonfragments.interfaces

import android.view.View
import com.example.movielistonfragments.model.MovieItem

interface OnItemClickListener {
    fun onItemClick(item: MovieItem)
    fun onFavoriteClick(item: MovieItem)
}