package com.example.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.interfaces.OnItemClickListener
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter(
    private val itemList: MutableList<MovieItem>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    var removePosition: Int = 0
    lateinit var removeMovieName: String
    lateinit var removeItem: MovieItem


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

    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
        // cash
        removePosition = viewHolder.adapterPosition
        removeMovieName = itemList.get(viewHolder.adapterPosition).movieName
        removeItem = itemList.get(viewHolder.adapterPosition)
        // remove item
        itemList.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
        // undo
        Snackbar.make(viewHolder.itemView, "$removeMovieName was deleted", Snackbar.LENGTH_LONG).setAction("UNDO"){
            itemList.add(removePosition, removeItem)
            notifyItemInserted(removePosition)
        }.show()
    }



    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.movie_image)
        val textNameView: TextView = itemView.findViewById(R.id.movie_name)
        val textDescriptionView: TextView = itemView.findViewById(R.id.movie_description)

        fun initialize(item: MovieItem, action: OnItemClickListener) {
            imageView.setImageResource(item.movieImage)
            textNameView.text = item.movieName
            textDescriptionView.text = item.movieDescription

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
                //TODO добавить интерфейс на обработку клика кнопки
//                action.onFavoriteButtonClick(item, adapterPosition)
                }


        }
    }

}