package com.example.movielistonfragments.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.adapters.RecyclerAdapter
import com.example.movielistonfragments.data.DataSetOfMovies
import com.example.movielistonfragments.decorations.TopSpaceItemDecoration
import com.example.movielistonfragments.interfaces.OnItemClickListener
import com.example.movielistonfragments.model.MovieItem

class MovieListFragment : Fragment() {

    private val list = DataSetOfMovies().createList()
    private val topSpaceItemDecoration = TopSpaceItemDecoration(30)
    private val favoriteList = ArrayList<MovieItem>()

    companion object {
        const val TAG = "MovieListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false) // return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_fragment)
        recyclerView.adapter = RecyclerAdapter(
            LayoutInflater.from(requireContext()), list
        ) {
            // тут какая то дичь
            if(activity is OnItemClickListener){
                (activity as? OnItemClickListener)?.onItemClick(it)
                (activity as? OnItemClickListener)?.onFavoriteClick(it)
                addToFavorite(it)
            }
        }
        //add decoration
        recyclerView.addItemDecoration(topSpaceItemDecoration)

        Log.i("SUMMARY", favoriteList.toString())
    }

    private fun addToFavorite(item: MovieItem) {
        if ((favoriteList.isNotEmpty() && checkForeach(favoriteList, item) == true) || favoriteList.isEmpty()) {
            Toast.makeText(requireContext(), "Movie ''${item.movieName}'' is added in favorite list", Toast.LENGTH_SHORT).show()
            favoriteList.add(item)
        }else{
            Toast.makeText(requireContext(), "Movie ''${item.movieName}'' is already added in favorite list", Toast.LENGTH_SHORT).show()
        }
    }

    // duplicate check
    private fun checkForeach(list: ArrayList<MovieItem>, item: MovieItem): Boolean {
        list.forEach {
            if (item == it) {
                return false
            }
        }
        return true
    }
}
