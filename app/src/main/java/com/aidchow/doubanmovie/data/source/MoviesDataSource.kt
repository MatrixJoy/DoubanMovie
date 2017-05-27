package com.aidchow.doubanmovie.data.source

import com.aidchow.doubanmovie.data.Movie

/**
 * Created by aidchow on 17-5-27.
 */
interface MoviesDataSource {
    interface LoadMoviesCallBack {
        fun onMoviesLoaded(movies: Movie)
        fun onMoviesLoadFailed(message: String)
    }


    fun getMovies(queryType: String, callBack: LoadMoviesCallBack)

    //load more movies
    fun loadMoreMovies(queryType: String, start: Int, callBack: LoadMoviesCallBack)


}