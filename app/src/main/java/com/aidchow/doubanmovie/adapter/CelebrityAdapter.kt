package com.aidchow.doubanmovie.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.aidchow.doubanmovie.BR
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.celebrity.CelebrityItemViewModel
import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.databinding.CelebrityItemBinding
import com.aidchow.doubanmovie.movies.MovieItemNavigator
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by aidchow on 17-5-27.
 */
class CelebrityAdapter(data: List<Celebrity.WorksBean>, layoutID: Int,
                       var navigator: MovieItemNavigator)
    : BaseQuickAdapter<Celebrity.WorksBean, CelebrityAdapter.CelebriyViewHolder>(layoutID, data) {

    override fun convert(helper: CelebriyViewHolder?, item: Celebrity.WorksBean?) {
        val binding: CelebrityItemBinding = helper?.getBinding() as CelebrityItemBinding
        val viewModel: CelebrityItemViewModel = CelebrityItemViewModel(helper.itemView.context)
        binding.viewModel = viewModel
        viewModel.setCelebrityWork(item!!)
        viewModel.setNavigator(navigator)
        binding.setVariable(BR._all, item)
        binding.executePendingBindings()
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding: CelebrityItemBinding? = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view: View = binding?.root!!
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }


    inner class CelebriyViewHolder(view: View?) : BaseViewHolder(view) {

        fun getBinding(): ViewDataBinding? {
            return itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as? ViewDataBinding
        }
    }
}