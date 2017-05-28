package com.aidchow.doubanmovie.retrofit

import com.aidchow.doubanmovie.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by aidchow on 17-5-26.
 */
interface APIServise {
    @GET("/v2/movie/{type}")
    fun getMovies(@Path("type") type: String): Call<Movie>

    @GET("/v2/movie/top250")
    fun loadTopW250Movies(@Query("start") start: Int,
                          @Query("count") count: Int): Call<Movie>

    //query text
    @GET("v2/movie/search")
    fun searchMovies(@Query("q") q: String): Call<Movie>


    @GET("/v2/movie/{type}")
    fun loadMoreMovies(@Path("type") type: String,
                       @Query("start") start: Int, @Query("count") count: Int = 10): Call<Movie>

    //query text
    @GET("v2/movie/search")
    fun searchMoviesLoadMore(@Query("q") q: String,
                             @Query("start") start: Int,
                             @Query("count") count: Int = 10): Call<Movie>
}