package com.example.movielist.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.model.MovieItem
import com.example.movielist.R
import com.example.movielist.interfaces.OnItemClickListener
import kotlinx.android.synthetic.main.item_movie.view.*

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.movie_image)
    val textNameView: TextView = itemView.findViewById(R.id.movie_name)
    val textDescriptionView: TextView = itemView.findViewById(R.id.movie_description)

    fun initialize(item: MovieItem, itemClickListener: OnItemClickListener) {
        imageView.setImageResource(item.movieImage)
        textNameView.text = item.movieName
        textDescriptionView.text = item.movieDescription

        itemView.setOnClickListener {
            itemClickListener.onItemClick(item, adapterPosition)
        }
        itemView.findViewById<View>(R.id.btn_favorite).setOnClickListener {
            itemClickListener.onFavoriteClick(item, adapterPosition)
            // change icon for imageButton on click
            itemView.btn_favorite.setImageResource(R.drawable.ic_baseline_favorite)
        }
    }
}