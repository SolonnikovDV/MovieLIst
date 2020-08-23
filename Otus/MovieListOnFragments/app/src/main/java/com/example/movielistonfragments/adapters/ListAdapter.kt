package com.example.movielistonfragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.holders.ItemHolder
import com.example.movielistonfragments.interfaces.OnItemClickListener
import com.example.movielistonfragments.model.MovieItem
import com.google.android.material.snackbar.Snackbar

class ListAdapter(
    private val layoutInflater: LayoutInflater,
    private val itemList: ArrayList<MovieItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position], listener)
    }
}