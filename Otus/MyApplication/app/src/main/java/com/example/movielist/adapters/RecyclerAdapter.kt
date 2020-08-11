package com.example.movielist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.model.MovieItem
import com.example.movielist.R
import com.example.movielist.holders.ItemHolder
import com.example.movielist.interfaces.OnItemClickListener
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter(
    private val itemList: MutableList<MovieItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemHolder>() {

    var removePosition: Int = 0
    var removeMovieName: String? = null
    lateinit var removeItem: MovieItem


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ItemHolder(itemView)
    }

    override fun getItemCount() = itemList.size



    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
//        val currentItem = itemList[position]
//        holder.imageView.setImageResource(currentItem.movieImage)
//        holder.textNameView.text = currentItem.movieName
//        holder.textDescriptionView.text = currentItem.movieDescription

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
        Snackbar.make(viewHolder.itemView, "$removeMovieName was deleted", Snackbar.LENGTH_LONG).setAction("UNDO"){
            itemList.add(removePosition, removeItem)
            notifyItemInserted(removePosition)
        }.show()
    }



//    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.movie_image)
//        val textNameView: TextView = itemView.findViewById(R.id.movie_name)
//        val textDescriptionView: TextView = itemView.findViewById(R.id.movie_description)
//
//        fun initialize(item: MovieItem, itemClickListener: OnItemClickListener) {
//            imageView.setImageResource(item.movieImage)
//            textNameView.text = item.movieName
//            textDescriptionView.text = item.movieDescription
//
//            itemView.setOnClickListener {
//                itemClickListener.onItemClick(item, adapterPosition)
//                }
//            itemView.findViewById<View>(R.id.btn_favorite).setOnClickListener {
//                itemClickListener.onFavoriteClick(item, adapterPosition)
//            }
//        }
//    }
}