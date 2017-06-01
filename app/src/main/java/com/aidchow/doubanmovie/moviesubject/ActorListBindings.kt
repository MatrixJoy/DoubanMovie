package com.aidchow.doubanmovie.moviesubject

import android.content.res.ColorStateList
import android.databinding.BindingAdapter
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import com.aidchow.doubanmovie.adapter.ActorAdapter
import com.aidchow.doubanmovie.data.Actor

/**
 * Created by aidchow on 17-5-30.
 */
object ActorListBindings {
    @BindingAdapter("actor:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<Actor>?) {
        val adapter: RecyclerView.Adapter<*>? = recyclerView.adapter
        if (adapter != null) {
            adapter as ActorAdapter
            if (items != null) {
                adapter.setNewData(items)
            }
        }
    }

    @BindingAdapter("app:fabTint")
    @JvmStatic fun setColorTint(fab: FloatingActionButton, color: Int) {
        fab.backgroundTintList = ColorStateList.valueOf(color)
    }
}