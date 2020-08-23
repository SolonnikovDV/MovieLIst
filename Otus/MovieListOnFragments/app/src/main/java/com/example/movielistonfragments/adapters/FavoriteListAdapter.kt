package com.example.movielistonfragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.holders.ItemHolderFavorite
import com.example.movielistonfragments.model.MovieItem
import com.google.android.material.snackbar.Snackbar

class FavoriteListAdapter(
    private val layoutInflater: LayoutInflater,
    private val itemList: ArrayList<MovieItem>
) : RecyclerView.Adapter<ItemHolderFavorite>() {

    var removePosition: Int = 0
    var removeMovieName: String? = null
    lateinit var removeItem: MovieItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolderFavorite {
        return ItemHolderFavorite(layoutInflater.inflate(R.layout.item_favorite_movie, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ItemHolderFavorite, position: Int) {
        holder.bind(itemList[position])
    }

    // remove favorite item from list, using in swipe to delete method
    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        // cash
        removePosition = viewHolder.adapterPosition
        removeMovieName = itemList.get(viewHolder.adapterPosition).movieName
        removeItem = itemList.get(viewHolder.adapterPosition)
        // remove item_movie
        itemList.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
        // undo
        Snackbar.make(viewHolder.itemView, "$removeMovieName was deleted", Snackbar.LENGTH_LONG).setAction("UNDO"){
            itemList.add(removePosition, removeItem)
            notifyItemInserted(removePosition)
        }.show()
    }
}