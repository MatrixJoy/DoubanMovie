package com.aidchow.doubanmovie.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.aidchow.doubanmovie.BR
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.adapter.MoviesAdapter.MovieViewHolder
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.databinding.MoviesItemBinding
import com.aidchow.doubanmovie.movies.MovieItemNavigator
import com.aidchow.doubanmovie.movies.MovieItemViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by aidchow on 17-5-27.
 */
class MoviesAdapter(data: List<Movie.SubjectsBean>, layoutID: Int,
                    var moviesRepository: MoviesRepository,
                    var navigator: MovieItemNavigator)
    : BaseQuickAdapter<Movie.SubjectsBean, MovieViewHolder>(layoutID, data) {

    override fun convert(helper: MovieViewHolder?, item: Movie.SubjectsBean?) {
        val binding:MoviesItemBinding = helper?.getBinding() as MoviesItemBinding
        val viewModel: MovieItemViewModel = MovieItemViewModel(helper.itemView.context,
                moviesRepository)
        binding.viewModel = viewModel
        viewModel.setMovie(item!!)
        viewModel.setPosition(helper.adapterPosition+1)
        viewModel.setNavigator(navigator)
        binding.setVariable(BR._all,item)
        binding.executePendingBindings()
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding: MoviesItemBinding? = DataBindingUtil.inflate(mLayoutInflater,layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view: View = binding?.root!!
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }


    inner class MovieViewHolder(view: View?) : BaseViewHolder(view) {

        fun getBinding(): ViewDataBinding? {
            return itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as? ViewDataBinding
        }
    }
}