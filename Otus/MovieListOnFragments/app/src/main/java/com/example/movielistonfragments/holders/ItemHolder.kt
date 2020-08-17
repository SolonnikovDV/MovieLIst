package com.example.movielistonfragments.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.model.MovieItem

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView: ImageView = itemView.findViewById(R.id.movie_image)
    val textNameView: TextView = itemView.findViewById(R.id.movie_name)
    val textDescriptionView: TextView = itemView.findViewById(R.id.movie_description)

    fun bind(item: MovieItem) {
        imageView.setImageResource(item.movieImage)
        textNameView.text = item.movieName
        textDescriptionView.text = item.movieDescription
    }
}