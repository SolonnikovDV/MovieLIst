package com.example.movielistonfragments.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.movielistonfragments.R
import com.example.movielistonfragments.model.MovieItem
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    lateinit var checkBox: CheckBox
    lateinit var textForCheckBox: TextView
    lateinit var textComment: EditText
    lateinit var commitButton : Button

    companion object{
        const val TAG = "DetailsFragment"
        const val MOVIE_NAME = "MOVIE_NAME"
        const val MOVIE_DESCRIPTION = "MOVIE_DESCRIPTION"
        const val MOVIE_IMAGE = "MOVIE_IMAGE"

    }

    fun newInstance (item: MovieItem) : DetailsFragment {
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

        doCheckBox()
        commit()
    }

    private fun doCheckBox()  {
        checkBox = check_box
        textForCheckBox = text_for_check_box
        textComment = text_comment
        checkBox.setOnClickListener{
            if(checkBox.isChecked) {
                textForCheckBox.setVisibility(View.INVISIBLE)
                textComment.setVisibility(View.VISIBLE)
            }else{
                textForCheckBox.setVisibility(View.VISIBLE)
            }}
    }

    private fun commit() {
        commitButton = commit_and_back_button
        commitButton.setOnClickListener{
            Toast.makeText(requireContext(), "You comment ''${textComment.text.toString()}'' was commited", Toast.LENGTH_LONG).show()
        }
    }
}