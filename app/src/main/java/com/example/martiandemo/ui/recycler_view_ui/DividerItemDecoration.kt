package com.example.martiandemo.ui.recycler_view_ui

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView


class DividerItemDecoration(var drawable: Drawable?, var paddingLeft: Int, var paddingRight: Int) : RecyclerView.ItemDecoration(){

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = paddingLeft
        val right = parent.width - paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + drawable?.intrinsicHeight!!
            drawable?.setBounds(left, top, right, bottom)
            drawable?.draw(c)
        }
    }
}