package com.example.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.interfaces.OnItemClickListener

class RecyclerAdapter(
    private val itemList: List<MovieItem>,
    private val clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        val currentItem = itemList[position]
//        holder.imageView.setImageResource(currentItem.movieImage)
//        holder.textNameView.text = currentItem.movieName
//        holder.textDescriptionView.text = currentItem.movieDescription

        holder.initialize(itemList.get(position), clickListener)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.movie_image)
        val textNameView: TextView = itemView.findViewById(R.id.movie_name)
        val textDescriptionView: TextView = itemView.findViewById(R.id.movie_description)

        fun initialize(item: MovieItem, action: OnItemClickListener) {
            imageView.setImageResource(item.movieImage)
            textNameView.text = item.movieName
            textDescriptionView.text = item.movieDescription

            itemView.setOnClickListener{action.onItemClick(item, adapterPosition)}
        }
    }

}