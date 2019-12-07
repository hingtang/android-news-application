package com.hing.newsapplication.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by HingTang on 2019-12-08.
 */
object BindingAdapter {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    /**
     * @param recyclerView  RecyclerView to bind to DividerItemDecoration
     * @param orientation 0 for LinearLayout.HORIZONTAL and 1 for LinearLayout.VERTICAL
     */
    @JvmStatic
    @BindingAdapter("divider")
    fun setDividerItemDecoration(recyclerView: RecyclerView, orientation: Int) {
        val itemDecoration = DividerItemDecoration(recyclerView.context, orientation)
        recyclerView.addItemDecoration(itemDecoration)
    }
}
