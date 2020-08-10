package com.example.movielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.pojo.MovieItem
import com.example.movielist.R
import com.example.movielist.holders.ItemFavorieHolder
import com.example.movielist.interfaces.OnItemClickListener
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapterFavorite(
    private val itemList: ArrayList<MovieItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemFavorieHolder>() {

    var removePosition: Int = 0
    var removeMovieName: String? = null
    lateinit var removeItem: MovieItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFavorieHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_movie, parent, false)
        return ItemFavorieHolder(itemView)
    }

    override fun getItemCount() = itemList.size


    override fun onBindViewHolder(holder: ItemFavorieHolder, position: Int) {
        holder.initialize(itemList.get(position), listener)
    }

    // remove item_movie, using in swipe to delete method
    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        // cash
        removePosition = viewHolder.adapterPosition
        removeMovieName = itemList.get(viewHolder.adapterPosition).movieName
        removeItem = itemList.get(viewHolder.adapterPosition)
        // remove item_movie
        itemList.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
        // undo
        Snackbar.make(viewHolder.itemView, "$removeMovieName was deleted", Snackbar.LENGTH_LONG)
            .setAction("UNDO") {
                itemList.add(removePosition, removeItem)
                notifyItemInserted(removePosition)
            }.show()
    }
}
