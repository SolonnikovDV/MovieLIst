package com.example.movielist.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.adapters.RecyclerAdapterFavorite
import com.example.movielist.decorations.TopSpaceItemDecoration
import com.example.movielist.interfaces.OnItemClickListener
import com.example.movielist.pojo.MovieItem


class FavoriteMovieActivity : AppCompatActivity(), OnItemClickListener {
    private var list = ArrayList<MovieItem>()
    private val topSpaceItemDecoration = TopSpaceItemDecoration(30)
    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))
    private lateinit var deleteIcon: Drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movie)

        deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete)!!

        // get items for favorite list
        val _list = intent.getParcelableArrayListExtra<MovieItem>("FavoriteMovie")
        // copy intent in list of items in the current activity
        list = ArrayList(_list)

        Log.i("_FAVORITE", list.toString())
        //TODO on recycler
        recyclerInit()

    }

    // initialize of recycler
    private fun recyclerInit() {
        val layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.favorite_recycler_view)
        // initialize adapter with updated list of items
        val adapter = RecyclerAdapterFavorite(list, this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(topSpaceItemDecoration)

        swipeToDelete(adapter, recyclerView)
    }

    fun swipeToDelete (adapter: RecyclerAdapterFavorite, recyclerView: RecyclerView) {
        // region swipe to delete
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
                (adapter as RecyclerAdapterFavorite).removeItem(viewHolder)
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
        itemTouchHelper.attachToRecyclerView(findViewById(R.id.favorite_recycler_view))
    }

    override fun onItemClick(item: MovieItem, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onFavoriteClick(item: MovieItem, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Back action")
            .setMessage("Are you sure you want to go back?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, which -> finish() })
            .setNegativeButton("No", null)
            .show()
    }

}