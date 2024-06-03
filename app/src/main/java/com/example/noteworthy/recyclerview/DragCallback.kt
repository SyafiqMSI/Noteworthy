package com.example.noteworthy.recyclerview

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteworthy.recyclerview.adapter.MakeListAdapter
import java.util.Collections

class DragCallback(private val elevation: Float, private val adapter: MakeListAdapter) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled() = false

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {}




    override fun onMove(view: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition
        Collections.swap(adapter.list, from, to)
        adapter.notifyItemMoved(from, to)
        return true
    }


    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        viewHolder.itemView.translationX = dX
        viewHolder.itemView.translationY = dY
        if (isCurrentlyActive) {
            viewHolder.itemView.elevation = elevation
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: ViewHolder) {
        viewHolder.itemView.translationX = 0f
        viewHolder.itemView.translationY = 0f
        viewHolder.itemView.elevation = 0f
    }
}