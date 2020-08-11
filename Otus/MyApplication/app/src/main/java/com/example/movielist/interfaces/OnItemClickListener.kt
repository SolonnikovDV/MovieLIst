package com.example.movielist.interfaces

import com.example.movielist.model.MovieItem

interface OnItemClickListener {
     fun onItemClick(item: MovieItem, position: Int)
     fun onFavoriteClick(item: MovieItem, position: Int)
}