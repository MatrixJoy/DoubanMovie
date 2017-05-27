package com.aidchow.doubanmovie.movies

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesRepository

/**
 * Created by aidchow on 17-5-27.
 */
class MovieItemViewModel(context: Context, moviesRepository: MoviesRepository) : BaseObservable() {
    val title: ObservableField<String> = ObservableField()

    val mContext: Context = context
    val mMovieRepositoy: MoviesRepository = moviesRepository

    val mMovieObservable: ObservableField<Movie> = ObservableField()

//    init {
//        mMovieObservable.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
//            override fun onPropertyChanged(p0: Observable?, p1: Int) {
//                val movie: Movie = mMovieObservable.get()
//            }
//        })
//    }

    fun setMovie(movie: Movie.SubjectsBean) {
        println(movie.title)
        title.set(movie.title)
    }

    @Bindable
    fun getMovieTitle(): String {
        return title.get()
    }
}