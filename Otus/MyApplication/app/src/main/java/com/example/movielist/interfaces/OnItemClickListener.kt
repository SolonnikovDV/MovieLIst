package com.example.movielist.interfaces

import com.example.movielist.pojo.MovieItem

interface OnItemClickListener {
     fun onItemClick(item: MovieItem, position: Int)
     fun onFavoriteClick(item: MovieItem, position: Int)
}