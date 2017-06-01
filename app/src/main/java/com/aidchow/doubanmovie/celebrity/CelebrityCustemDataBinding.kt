package com.aidchow.doubanmovie.celebrity

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.aidchow.doubanmovie.adapter.CelebrityAdapter
import com.aidchow.doubanmovie.data.Celebrity

/**
 * Created by aidchow on 17-5-31.
 */
object CelebrityCustemDataBinding {
    @BindingAdapter("celebrity:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<Celebrity.WorksBean>?) {
        val adapter: RecyclerView.Adapter<*>? = recyclerView.adapter
        if (adapter != null) {
            adapter as CelebrityAdapter
            if (items != null) {
                adapter.setNewData(items)
            }
        }
    }

}