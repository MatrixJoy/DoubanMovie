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


    //load douban movie top250 ranking
    fun loadTop250Movies(start: Int, count: Int = 10, callBack: LoadMoviesCallBack)

    //search movies by query text
    fun searchMovies(queryText: String, callBack: LoadMoviesCallBack)


}