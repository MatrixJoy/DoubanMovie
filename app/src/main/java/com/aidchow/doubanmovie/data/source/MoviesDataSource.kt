package com.aidchow.doubanmovie.data.source

import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.MovieSubject

/**
 * Created by aidchow on 17-5-27.
 */
interface MoviesDataSource {

    interface LoadMoviesCallBack {
        fun onMoviesLoaded(movies: Movie)
        fun onMoviesLoadFailed(message: String)
    }

    interface LoadMovieSubjectCallBack {
        fun onMovieSubjectLoaded(movieSubject: MovieSubject)

        fun onMovieSubjectLoadFailed(message: String)
    }

    interface LoadCelebirtyCallBack {
        fun onCelebirtyLoaded(celebrity: Celebrity)

        fun onCelebirtyLoadFailed(message: String)
    }

    fun getMovies(queryType: String, callBack: LoadMoviesCallBack)

    //load more movies
    fun loadMoreMovies(queryType: String, start: Int, callBack: LoadMoviesCallBack)

    //load douban movie top250 ranking
    fun loadTop250Movies(start: Int, callBack: LoadMoviesCallBack)

    //search movies by query text
    fun searchMovies(queryText: String, callBack: LoadMoviesCallBack)

    fun loadMovieSubject(movieId: Int, callBack: LoadMovieSubjectCallBack)

    fun loadCelebrity(starId: Int, callBack: LoadCelebirtyCallBack)
}