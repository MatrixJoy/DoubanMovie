package com.aidchow.doubanmovie.data.source.remote

import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesDataSource
import com.aidchow.doubanmovie.retrofit.API
import com.aidchow.doubanmovie.retrofit.APIServise
import com.aidchow.doubanmovie.retrofit.BaseRetrofit
import com.aidchow.doubanmovie.retrofit.CustemCallBack
import retrofit2.Response

/**
 * Created by aidchow on 17-5-27.
 */
class MoviesRemoteDataSource private constructor() : MoviesDataSource {
    private val movieTypes = listOf("us_box", "top250")
    private val call: APIServise = BaseRetrofit.retrofit(API.BASE_URL).create(APIServise::class.java)

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

    /**
     *access the data from network
     * @queryType the text of query
     * @callBack call back when request is response
     */
    override fun getMovies(queryType: String, callBack: MoviesDataSource.LoadMoviesCallBack) {

        when (queryType) {
            "top250" -> call.getMovies(queryType).enqueue(object : CustemCallBack<Movie>() {
                override fun onSuccess(response: Response<Movie>?) {
                    callBack.onMoviesLoaded(response?.body()!!)
                }

                override fun onFail(message: String) {
                    callBack.onMoviesLoadFailed(message)
                }

            })
//            else -> call.searchMovies(queryType).enqueue(object : CustemCallBack<Movie>() {
//                override fun onSuccess(response: Response<Movie>?) {
//                    callBack.onMoviesLoaded(response?.body()!!)
//                }
//
//                override fun onFail(message: String) {
//                    callBack.onMoviesLoadFailed(message)
//                }
//            })
        }
    }

    /**
     *load more data from network
     * @queryType the query text
     * @start the next items start position
     */
    override fun loadMoreMovies(queryType: String, start: Int, callBack: MoviesDataSource.LoadMoviesCallBack) {
        when (queryType) {
            in movieTypes -> call.loadMoreMovies(queryType, start)
                    .enqueue(object : CustemCallBack<Movie>() {
                        override fun onSuccess(response: Response<Movie>?) {
                            callBack.onMoviesLoaded(response?.body()!!)
                        }

                        override fun onFail(message: String) {
                            callBack.onMoviesLoadFailed(message)
                        }
                    })
            else -> call.searchMoviesLoadMore(queryType, start)
                    .enqueue(object : CustemCallBack<Movie>() {
                        override fun onSuccess(response: Response<Movie>?) {
                            callBack.onMoviesLoaded(response?.body()!!)
                        }

                        override fun onFail(message: String) {
                            callBack.onMoviesLoadFailed(message)
                        }
                    })
        }

    }
}