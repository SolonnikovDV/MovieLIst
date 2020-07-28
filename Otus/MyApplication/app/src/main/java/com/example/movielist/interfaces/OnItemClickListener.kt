package com.example.movielist.interfaces

import com.example.movielist.MovieItem

interface OnItemClickListener {
     fun onItemClick(item: MovieItem, position: Int)
}