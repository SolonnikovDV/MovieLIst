package com.example.movielistonfragments.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.model.MovieItem

class ItemHolderFavorite(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView: ImageView = itemView.findViewById(R.id.favorite_movie_image)
    val textNameView: TextView = itemView.findViewById(R.id.favorite_movie_name)

    fun bind(item: MovieItem) {
        imageView.setImageResource(item.movieImage)
        textNameView.text = item.movieName
    }
}