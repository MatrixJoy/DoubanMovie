package com.aidchow.doubanmovie.data.source

import com.aidchow.doubanmovie.data.MovieSubject

/**
 * Created by aidchow on 17-5-29.
 */
interface MovieSubjectDataSource {
    interface LoadMovieSubjectCallBack {
        fun onMovieSubjectLoaded(movieSubject: MovieSubject)

        fun onMovieSubjectLoadFailed(message: String)
    }

    fun loadMovieSubject(movieId: Int, callBack: LoadMovieSubjectCallBack)
}