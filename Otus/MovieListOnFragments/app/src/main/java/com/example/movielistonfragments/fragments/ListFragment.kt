package com.example.movielistonfragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.adapters.ListAdapter
import com.example.movielistonfragments.data.DataSetOfMovies
import com.example.movielistonfragments.decorations.TopSpaceItemDecoration
import com.example.movielistonfragments.interfaces.OnItemClickListener

class ListFragment : Fragment() {
    private val list = DataSetOfMovies().createList()
    private val topSpaceItemDecoration = TopSpaceItemDecoration(30)

    private var listener: OnItemClickListener? = null

    companion object {
        const val TAG = "MovieListFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_movie_list, container, false) // return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_fragment)
        val layoutInflater = LayoutInflater.from(requireContext())
        val adapter = ListAdapter(layoutInflater, list, activity as OnItemClickListener)
        recyclerView.adapter = adapter
        //add decoration
        recyclerView.addItemDecoration(topSpaceItemDecoration)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity is OnItemClickListener) listener = activity as OnItemClickListener
        else throw Exception("Activity must implement interfaces")
    }
}
