package com.aidchow.doubanmovie.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.aidchow.doubanmovie.BR
import com.aidchow.doubanmovie.adapter.MoviesAdapter.MovieViewHolder
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.databinding.MoviesItemBinding
import com.aidchow.doubanmovie.movies.MovieItemViewModel

/**
 * Created by aidchow on 17-5-27.
 */
class MoviesAdapter(var data: List<Movie.SubjectsBean>, var moviesRepository: MoviesRepository) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
        holder?.bind(data[position])
    }

    fun setDatas(data: List<Movie.SubjectsBean>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: MoviesItemBinding = MoviesItemBinding.inflate(inflater, parent, false)
        val viewModel: MovieItemViewModel = MovieItemViewModel(parent.context, moviesRepository)
        binding.viewModel = viewModel
        return MovieViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MovieViewHolder(val binding: ViewDataBinding, var viewModel: MovieItemViewModel) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Movie.SubjectsBean) {
            viewModel.setMovie(data)
            binding.setVariable(BR.movie, data)
            binding.executePendingBindings()
        }
    }
}