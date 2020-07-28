package com.example.movielist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.movielist.R
import com.example.movielist.databinding.ActivityMovieDetailsBinding
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        image_movie_details.setImageResource((intent.getStringExtra("MovieImage")).toInt())
        movie_name_details.text = intent.getStringExtra("MovieName")
        movie_description_details.text = intent.getStringExtra("MovieFullDescription")
    }
}