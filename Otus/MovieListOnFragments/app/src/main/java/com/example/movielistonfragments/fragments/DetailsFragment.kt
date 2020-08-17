package com.example.movielistonfragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movielistonfragments.R
import com.example.movielistonfragments.model.MovieItem
import kotlinx.android.synthetic.main.fragment_details.image_movie_details
import kotlinx.android.synthetic.main.fragment_details.movie_description_details
import kotlinx.android.synthetic.main.fragment_details.movie_name_details

class DetailsFragment : Fragment() {

    companion object{
        const val TAG = "DetailsFragment"
        const val MOVIE_NAME = "MOVIE_NAME"
        const val MOVIE_DESCRIPTION = "MOVIE_DESCRIPTION"
        const val MOVIE_IMAGE = "MOVIE_IMAGE"

    }

    fun newInstance (item: MovieItem) : DetailsFragment{
        val fragment = DetailsFragment()
        val bundle = Bundle()

        bundle.putString(MOVIE_NAME, item.movieName)
        bundle.putString(MOVIE_DESCRIPTION, item.movieFullDescription)
        bundle.putInt(MOVIE_IMAGE, item.movieImage)
        fragment.arguments = bundle

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = true
        return inflater.inflate(R.layout.fragment_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movie_name_details.text = arguments?.getString(MOVIE_NAME, "NULL NAME")
        movie_description_details.text = arguments?.getString(MOVIE_DESCRIPTION, "NULL DESCRIPTION")
        arguments?.getInt(MOVIE_IMAGE, 0)?.let { image_movie_details.setImageResource(it) }
    }
}