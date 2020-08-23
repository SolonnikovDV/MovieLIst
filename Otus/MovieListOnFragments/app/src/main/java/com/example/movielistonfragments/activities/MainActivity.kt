package com.example.movielistonfragments.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movielistonfragments.R
import com.example.movielistonfragments.fragments.DetailsFragment
import com.example.movielistonfragments.fragments.FavoriteFragment
import com.example.movielistonfragments.fragments.ListFragment
import com.example.movielistonfragments.interfaces.OnItemClickListener
import com.example.movielistonfragments.model.MovieItem
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), OnItemClickListener {
    private val favoriteList = ArrayList<MovieItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // start MovieListFragment
        openFragment(R.id.fragment_container, ListFragment(), ListFragment.TAG)
        //start bottom navigation bar
        bottomNavigationInit()
        //start top tool bar
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun bottomNavigationInit(){
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        bottomNavigation.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                R.id.home->openFragment(R.id.fragment_container, ListFragment(), ListFragment.TAG)
                R.id.favorite->openFragment(R.id.fragment_container, FavoriteFragment().newInstance(favoriteList), FavoriteFragment.TAG)
            }
            true
        }
    }
    // initialize toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // toolbar actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when (itemView) {
            R.id.invite_friend -> inviteFriend()
        }
        return false
    }

    private fun openFragment(fragmentContainer: Int, fragment: Fragment, fragmentTag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainer, fragment, fragmentTag)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        // set backStackEntryCount > 1, cause backStackEntryCount = 1 -> MainActivity (empty layout)
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            Toast.makeText(this, "BackStack is empty", Toast.LENGTH_SHORT).show()
        }
    }

    // start DetailsFragment with click on item
    override fun onItemClick(item: MovieItem, position: Int) {
        openFragment(R.id.fragment_container, DetailsFragment().newInstance(item), DetailsFragment.TAG)
    }

    // add favorite items in a list
    override fun onFavoriteClick(item: MovieItem, position: Int) {
        // FavoriteFragment will start in BottomNavigationBar
        addToFavorite(item)
        Log.i("_FAVOR", favoriteList.toString())
    }

    //summary favorite items
    private fun addToFavorite(item: MovieItem) {
        if ((favoriteList.isNotEmpty() && checkForeach(favoriteList, item) == true) || favoriteList.isEmpty()) {
            Toast.makeText(this, "Movie ''${item.movieName}'' is added in favorite list", Toast.LENGTH_SHORT).show()
            favoriteList.add(item)
        }else{
            Toast.makeText(this, "Movie ''${item.movieName}'' is already added in favorite list", Toast.LENGTH_SHORT).show()
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

    // invite friend action in toolbar menu
    private fun inviteFriend() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("send to: ")
        intent.type = "plane/text"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, "")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        Toast.makeText(this, "You've send an invite", Toast.LENGTH_SHORT).show()
        startActivity(Intent.createChooser(intent, ""))
    }
}