package com.example.movielist.activities

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.*
import com.example.movielist.decorations.TopSpaceItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import com.example.movielist.interfaces.OnItemClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val list = Storage().createList()
    private val adapter = RecyclerAdapter(list, this)
    private val topSpaceItemDecoration = TopSpaceItemDecoration(30)
    private lateinit var fab: FloatingActionButton
    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))
    private lateinit var deleteIcon: Drawable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        deleteIcon = ContextCompat.getDrawable(this, R.drawable.ic_delete)!!

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.addItemDecoration(topSpaceItemDecoration)

        inviteFriend()

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
                    swipeBackground.setBounds(itemView.left, itemView.top, dX.toInt(), itemView.bottom)
                    deleteIcon.setBounds(
                        itemView.left + iconMargin,
                        itemView.top + iconMargin,
                        itemView.left + iconMargin + deleteIcon.intrinsicWidth,
                        itemView.bottom - iconMargin
                    )
                } else {
                    swipeBackground.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
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
                    c.clipRect(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                }

                deleteIcon.draw(c)

                c.restore()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }


        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(findViewById(R.id.recycler_view))

    }

    fun insertItem(view: View) {
        val index: Int = list.size
        val indexPosition = index + 1
        val newItem = MovieItem(
            R.drawable.ic_baseline_adb_24,
            "New movie item",
            "New item at position $indexPosition",
            "null"
        )
        list.add(index, newItem)
        adapter.notifyItemInserted(index)

    }

    fun showDetails(view: View) {
        //TODO
        // isSelected -> onClickListener
    }

    override fun onItemClick(item: MovieItem, position: Int) {
        Toast.makeText(this, "Movie ''${item.movieName}'' choosing", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MovieDetailsActivity::class.java)
        //TODO change text color

        intent.putExtra("MovieImage", item.movieImage.toString())
        intent.putExtra("MovieName", item.movieName)
        intent.putExtra("MovieFullDescription", item.movieFullDescription)
        startActivity(intent)
    }

    private fun inviteFriend() {
        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("send to: ")
            intent.type = "plane/text"
            intent.putExtra(Intent.EXTRA_MIME_TYPES, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")
            Toast.makeText(this, "You've send an invite", Toast.LENGTH_SHORT).show()
            startActivity(Intent.createChooser(intent, ""))
        }
    }

}