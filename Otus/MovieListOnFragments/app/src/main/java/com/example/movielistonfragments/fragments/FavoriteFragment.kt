package com.example.movielistonfragments.fragments

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.movielistonfragments.R
import com.example.movielistonfragments.adapters.FavoriteListAdapter
import com.example.movielistonfragments.decorations.TopSpaceItemDecoration
import com.example.movielistonfragments.model.MovieItem

class FavoriteFragment : Fragment() {
    private val topSpaceItemDecoration = TopSpaceItemDecoration(30)
    private var list = ArrayList<MovieItem>()
    private var swipeBackground: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000"))
    private lateinit var deleteIcon : Drawable


//    test
//    private val list = DataSetOfMovies().createList()

    companion object{
        const val TAG = "FavoriteFragment"
        const val MOVIE_NAME = "MOVIE_NAME"
    }

    fun newInstance(_list: ArrayList<MovieItem>): Fragment{
        val fragment = FavoriteFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList(MOVIE_NAME, _list)
        fragment.arguments = bundle

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list = arguments?.getParcelableArrayList(MOVIE_NAME)!!
        Log.i("_GETFAV", list.toString())

        val recyclerView = view.findViewById<RecyclerView>(R.id.favorite_recycler_view)
        val layoutInflater = LayoutInflater.from(requireContext())
        val adapter = FavoriteListAdapter(layoutInflater, list)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(topSpaceItemDecoration)

        deleteIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete)!!
        swipeToDelete(adapter, recyclerView)

    }

    private fun swipeToDelete(adapter: FavoriteListAdapter, recyclerView: RecyclerView) {
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
                (adapter as FavoriteListAdapter).removeItem(viewHolder)
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
}