package com.aidchow.doubanmovie.data.source.remote

import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesDataSource
import com.aidchow.doubanmovie.retrofit.API
import com.aidchow.doubanmovie.retrofit.APIService
import com.aidchow.doubanmovie.retrofit.BaseRetrofit
import com.aidchow.doubanmovie.retrofit.CustemCallBack
import retrofit2.Call
import retrofit2.Response

/**
 * Created by aidchow on 17-5-27.
 */
class MoviesRemoteDataSource private constructor() : MoviesDataSource {


    companion object {
        private var INSTANCE: MoviesRemoteDataSource? = null
        val instance: MoviesRemoteDataSource
            get() {
                if (INSTANCE == null) {
                    INSTANCE = MoviesRemoteDataSource()
                }
                return INSTANCE as MoviesRemoteDataSource
            }
    }


    override fun searchMovies(queryText: String, callBack: MoviesDataSource.LoadMoviesCallBack) {
        val call: Call<Movie> = BaseRetrofit.retrofit(API.BASE_URL).create(APIService::class.java)
                .searchMovies(queryText)
        call.enqueue(object : CustemCallBack<Movie>() {
            override fun onSuccess(response: Response<Movie>?) {
                callBack.onMoviesLoaded(response?.body()!!)
            }

            override fun onFail(message: String) {
                callBack.onMoviesLoadFailed(message)
            }
        })
    }

    override fun loadTop250Movies(start: Int, count: Int, callBack: MoviesDataSource.LoadMoviesCallBack) {
        val call: Call<Movie> = BaseRetrofit.retrofit(API.BASE_URL).create(APIService::class.java)
                .loadTopW250Movies(start, count)
        call.enqueue(object : CustemCallBack<Movie>() {
            override fun onSuccess(response: Response<Movie>?) {
                callBack.onMoviesLoaded(response?.body()!!)
            }

            override fun onFail(message: String) {
                callBack.onMoviesLoadFailed(message)
            }
        })
    }
}