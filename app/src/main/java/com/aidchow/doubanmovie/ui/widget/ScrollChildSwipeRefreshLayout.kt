package com.aidchow.doubanmovie.ui.widget

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import android.view.View

/**
 * Created by aidchow on 17-5-27.
 * Extends [SwipeRefreshLayout]to support non-direct descendant scrolling views.
 * <p>
 * [SwipeRefreshLayout] works as expected when a scroll view is a direct child: it triggers
 * the refresh only when the view is on top. This class adds a way [#setScrollUpChild] to
 * define which view controls this behavior.
 */
class ScrollChildSwipeRefreshLayout : SwipeRefreshLayout {
    private var mScrollUpChild: View? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attributeSet: AttributeSet?) : super(context, attributeSet)

    override fun canChildScrollUp(): Boolean {
        if (mScrollUpChild != null) {
            return ViewCompat.canScrollVertically(mScrollUpChild, -1)
        }
        return super.canChildScrollUp()
    }

    fun setScrollUpchild(view: View) {
        mScrollUpChild = view
    }
}