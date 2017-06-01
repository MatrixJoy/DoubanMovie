package com.aidchow.doubanmovie.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.aidchow.doubanmovie.BR
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.data.Actor
import com.aidchow.doubanmovie.databinding.ActorItemBinding
import com.aidchow.doubanmovie.moviesubject.ActorItemNavigator
import com.aidchow.doubanmovie.moviesubject.ActorItemViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by aidchow on 17-5-30.
 */
class ActorAdapter(layoutResId: Int,
                   var navigator: ActorItemNavigator,
                   data: MutableList<Actor>?) :
        BaseQuickAdapter<Actor, ActorAdapter.ActorViewHolder>(layoutResId, data) {
    override fun convert(helper: ActorViewHolder?, item: Actor?) {
        val binding: ActorItemBinding = helper?.getBinding() as ActorItemBinding
        val viewModel: ActorItemViewModel = ActorItemViewModel()
        binding.viewModel = viewModel
        viewModel.setActor(item!!)
        viewModel.setNavigator(navigator)
        binding.setVariable(BR._all, item)
        binding.executePendingBindings()
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {

        val binding: ActorItemBinding? = DataBindingUtil.inflate(mLayoutInflater,
                layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view: View = binding?.root!!
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }


    inner class ActorViewHolder(view: View?) : BaseViewHolder(view) {
        fun getBinding(): ViewDataBinding? {
            return itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as? ViewDataBinding
        }
    }
}