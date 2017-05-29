package com.aidchow.doubanmovie.data.source.remote

import com.aidchow.doubanmovie.data.MovieSubject
import com.aidchow.doubanmovie.data.source.MovieSubjectDataSource
import com.aidchow.doubanmovie.retrofit.API
import com.aidchow.doubanmovie.retrofit.APIService
import com.aidchow.doubanmovie.retrofit.BaseRetrofit
import com.aidchow.doubanmovie.retrofit.CustemCallBack
import retrofit2.Call
import retrofit2.Response

/**
 * Created by aidchow on 17-5-29.
 */
class MovieSubjectRemoteDataSource private constructor() : MovieSubjectDataSource {

    companion object {
        private var INSTANCE: MovieSubjectRemoteDataSource? = null
        val instance: MovieSubjectRemoteDataSource
            get() {
                if (INSTANCE == null) {
                    INSTANCE = MovieSubjectRemoteDataSource()
                }
                return INSTANCE as MovieSubjectRemoteDataSource
            }
    }

    override fun loadMovieSubject(movieId: Int, callBack: MovieSubjectDataSource.LoadMovieSubjectCallBack) {
        val call: Call<MovieSubject> = BaseRetrofit
                .retrofit(API.BASE_URL)
                .create(APIService::class.java)
                .loadMovieSubject(movieId)
        call.enqueue(object : CustemCallBack<MovieSubject>() {
            override fun onSuccess(response: Response<MovieSubject>?) {
                callBack.onMovieSubjectLoaded(response?.body()!!)
            }

            override fun onFail(message: String) {
                callBack.onMovieSubjectLoadFailed(message)
            }
        })
    }
}