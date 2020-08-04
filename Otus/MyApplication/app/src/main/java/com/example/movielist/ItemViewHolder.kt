package com.example.movielist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.interfaces.OnItemClickListener

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.movie_image)
    val textNameView: TextView = itemView.findViewById(R.id.movie_name)
    val textDescriptionView: TextView = itemView.findViewById(R.id.movie_description)

    fun initialize(item: MovieItem, action: OnItemClickListener) {
        imageView.setImageResource(item.movieImage)
        textNameView.text = item.movieName
        textDescriptionView.text = item.movieDescription

        itemView.setOnClickListener { action.onItemClick(item, adapterPosition) }
    }
}