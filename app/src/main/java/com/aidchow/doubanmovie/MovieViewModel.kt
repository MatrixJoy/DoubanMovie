package com.aidchow.doubanmovie

import android.content.Context
import android.databinding.BaseObservable
import com.aidchow.doubanmovie.data.source.MoviesRepository

/**
 * Created by aidchow on 17-5-29.
 */
abstract class MovieViewModel(var context: Context, var moviesRepository: MoviesRepository)
    : BaseObservable() {



    private val mContex: Context = context.applicationContext
    private val mMoviesRepository: MoviesRepository = moviesRepository


}