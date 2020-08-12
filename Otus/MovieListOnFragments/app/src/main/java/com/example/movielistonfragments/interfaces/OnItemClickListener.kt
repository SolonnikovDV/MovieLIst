package com.example.movielistonfragments.interfaces

import com.example.movielistonfragments.model.MovieItem

interface OnItemClickListener {
     fun onItemClick(item: MovieItem, position: Int)
     fun onFavoriteClick(item: MovieItem, position: Int)
}