package com.example.movielistonfragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.holders.ItemHolder
import com.example.movielistonfragments.model.MovieItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_movie.view.*

class RecyclerAdapter(
    private val layoutInflater: LayoutInflater,
    private val itemList: ArrayList<MovieItem>,
    private val listener: ((movieItem: MovieItem) -> Unit)?) : RecyclerView.Adapter<ItemHolder>() {

    var removePosition: Int = 0
    var removeMovieName: String? = null
    lateinit var removeItem: MovieItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position])

        // item click listener
        holder.itemView.setOnClickListener {
            listener?.invoke(itemList[position])
        }
        // favorite button click listener
        holder.itemView.findViewById<View>(R.id.btn_favorite).setOnClickListener {
            listener?.invoke(itemList[position])
            holder.itemView.btn_favorite.setImageResource(R.drawable.ic_baseline_favorite)
        }
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
        Snackbar.make(viewHolder.itemView, "$removeMovieName was deleted", Snackbar.LENGTH_LONG).setAction("UNDO"){
            itemList.add(removePosition, removeItem)
            notifyItemInserted(removePosition)
        }.show()
    }
}