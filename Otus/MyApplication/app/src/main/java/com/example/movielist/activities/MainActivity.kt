package com.example.movielist.activities

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.*
import com.example.movielist.adapters.RecyclerAdapter
import com.example.movielist.data.DataSetOfMovies
import com.example.movielist.decorations.TopSpaceItemDecoration
import com.example.movielist.interfaces.OnItemClickListener
import com.example.movielist.model.MovieItem

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val list = DataSetOfMovies().createList()
    private val adapter = RecyclerAdapter(list, this)
    private val topSpaceItemDecoration = TopSpaceItemDecoration(30)
    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))
    private lateinit var deleteIcon: Drawable
    private val favoriteList = ArrayList<MovieItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete)!!

        recyclerInit()
        swipeToDelete(adapter, findViewById(R.id.recycler_view))

        setSupportActionBar(findViewById(R.id.toolbar))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // toolbar actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when (itemView) {
            R.id.insert_item -> insertItem()
            R.id.invite_friend -> inviteFriend()
            R.id.show_favorite -> showFavoriteList()
        }
        return false
    }

    // initialize of recycler
    private fun recyclerInit() {
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(topSpaceItemDecoration)

        // region pagination
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    findViewById<View>(R.id.progress_bar).visibility = View.VISIBLE
                } else findViewById<View>(R.id.progress_bar).visibility = View.GONE
            }
        })
        // endregion
    }

    private fun swipeToDelete(adapter: RecyclerAdapter, recyclerView: RecyclerView) {
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                (adapter as RecyclerAdapter).removeItem(viewHolder)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView

                val iconMargin = (itemView.height - deleteIcon.intrinsicHeight) / 2

                if (dX > 0) {
                    swipeBackground.setBounds(
                        itemView.left,
                        itemView.top,
                        dX.toInt(),
                        itemView.bottom
                    )
                    deleteIcon.setBounds(
                        itemView.left + iconMargin,
                        itemView.top + iconMargin,
                        itemView.left + iconMargin + deleteIcon.intrinsicWidth,
                        itemView.bottom - iconMargin
                    )
                } else {
                    swipeBackground.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    deleteIcon.setBounds(
                        itemView.right - iconMargin - deleteIcon.intrinsicWidth,
                        itemView.top + iconMargin,
                        itemView.right - iconMargin,
                        itemView.bottom - iconMargin
                    )
                }

                swipeBackground.draw(c)

                c.save()

                if (dX > 0) {
                    c.clipRect(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                } else {
                    c.clipRect(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                }

                deleteIcon.draw(c)

                c.restore()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        // endregion

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    // inset item_movie in recycler
    private fun insertItem() {
        val index: Int = list.size
        val indexPosition = index + 1
        val newItem = MovieItem(
            R.drawable.ic_baseline_adb_24,
            "New movie item_movie",
            "New item_movie at position $indexPosition",
            "null"
        )
        list.add(index, newItem)
        adapter.notifyItemInserted(index)

        Toast.makeText(
            this,
            "New item was added at $indexPosition position",
            Toast.LENGTH_SHORT
        ).show()
    }

    // show details of movie
    override fun onItemClick(item: MovieItem, position: Int) {
        Toast.makeText(this, "Movie ''${item.movieName}'' choosing", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MovieDetailsActivity::class.java)

        intent.putExtra("MovieImage", item.movieImage.toString())
        intent.putExtra("MovieName", item.movieName)
        intent.putExtra("MovieFullDescription", item.movieFullDescription)
        startActivity(intent)
    }

    // add item_movie in the favorite list + checking ArrayList for null & duplicate
    override fun onFavoriteClick(item: MovieItem, position: Int) {
        // checking ArrayList
        if ((favoriteList.isNotEmpty() && checkForeach(favoriteList, item) == true) || favoriteList.isEmpty()) {
            favoriteList.add(item)
            Toast.makeText(
                this,
                "Movie ''${item.movieName}'' is added in favorite list",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            Toast.makeText(
                this,
                "Movie ''${item.movieName}'' is already added in favorite list",
                Toast.LENGTH_LONG
            ).show()
        }

        Log.i("_LIST", favoriteList.toString())
    }

    fun showFavoriteList(){
        val intent = Intent(this, FavoriteMovieActivity::class.java)
        intent.putExtra("FavoriteMovie", favoriteList)
        startActivity(intent)
    }

    // duplicate check
    private fun checkForeach(_list: ArrayList<MovieItem>, _item: MovieItem): Boolean {
        _list.forEach {
            if (_item == it) {
                return false
            }
        }
        return true
    }

    // send action
    fun inviteFriend() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("send to: ")
        intent.type = "plane/text"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, "")
        intent.putExtra(Intent.EXTRA_TEXT, "")
        Toast.makeText(this, "You've send an invite", Toast.LENGTH_SHORT).show()
        startActivity(Intent.createChooser(intent, ""))
    }

    //TODO make a button to show the favorite list

}