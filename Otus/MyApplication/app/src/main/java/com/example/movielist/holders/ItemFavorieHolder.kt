package com.example.movielist.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.model.MovieItem
import com.example.movielist.R
import com.example.movielist.interfaces.OnItemClickListener

class ItemFavorieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.favorite_movie_image)
    val textNameView: TextView = itemView.findViewById(R.id.favorite_movie_name)

    fun initialize(item: MovieItem, itemClickListener: OnItemClickListener) {
        imageView.setImageResource(item.movieImage)
        textNameView.text = item.movieName

    }
}