package com.example.movielistonfragments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.adapters.RecyclerAdapter
import com.example.movielistonfragments.decorations.TopSpaceItemDecoration
import com.example.movielistonfragments.model.MovieItem
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_favorite_movie.*

class FavoriteFragment : Fragment() {
    private val topSpaceItemDecoration = TopSpaceItemDecoration(30)
    private var list = ArrayList<MovieItem>()

    companion object{
        const val TAG = "FavoriteFragment"
        const val MOVIE_NAME = "MOVIE_NAME"
        const val MOVIE_IMAGE = "MOVIE_IMAGE"
    }

    fun newInstance(item: MovieItem): Fragment{
        val fragment = FavoriteFragment()
        val bundle = Bundle()

        fragment.arguments = bundle

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.favorite_recycler_view)
        recyclerView.adapter = RecyclerAdapter(LayoutInflater.from(requireContext()), list, {})

//        arguments?.getInt(MOVIE_IMAGE, 0)?.let { favorite_movie_image.setImageResource(it) }
//        favorite_movie_name.text = arguments?.getString(MOVIE_NAME, "NULL NAME")

        recyclerView.addItemDecoration(topSpaceItemDecoration)
    }

}