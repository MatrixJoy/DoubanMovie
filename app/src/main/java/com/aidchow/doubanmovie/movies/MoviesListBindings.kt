package com.aidchow.doubanmovie.movies

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.aidchow.doubanmovie.adapter.MoviesAdapter
import com.aidchow.doubanmovie.data.Movie

/**
 * Created by aidchow on 17-5-27.
 */
object MoviesListBindings {
    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<Movie.SubjectsBean>) {
        val adapter: RecyclerView.Adapter<*>? = recyclerView.adapter
        if (adapter != null) {
            adapter as MoviesAdapter
            adapter.setDatas(data = items)
            println(items.toString())
        }
    }
}