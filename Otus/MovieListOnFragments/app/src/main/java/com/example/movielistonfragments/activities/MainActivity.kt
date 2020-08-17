package com.example.movielistonfragments.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movielistonfragments.*
import com.example.movielistonfragments.fragments.FavoriteFragment
import com.example.movielistonfragments.fragments.DetailsFragment
import com.example.movielistonfragments.fragments.MovieListFragment
import com.example.movielistonfragments.interfaces.OnItemClickListener
import com.example.movielistonfragments.model.MovieItem


class MainActivity : AppCompatActivity(), OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start MovieListFragment
        openFragment(R.id.fragment_container, MovieListFragment(), MovieListFragment.TAG)

    }

    private fun openFragment(fragmentContainer: Int, fragment: Fragment, fragmentTag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainer, fragment, fragmentTag)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            Toast.makeText(this, "BackStack is empty", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(item: MovieItem) {
        // start MovieDetailsFragment
        openFragment(R.id.fragment_container, DetailsFragment().newInstance(item), DetailsFragment.TAG)
    }

    override fun onFavoriteClick(item: MovieItem) {
        // start FavoriteFragment
        openFragment(R.id.fragment_container, FavoriteFragment().newInstance(item), FavoriteFragment.TAG)
    }

}