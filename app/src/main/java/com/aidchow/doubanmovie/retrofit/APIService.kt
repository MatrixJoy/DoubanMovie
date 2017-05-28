package com.aidchow.doubanmovie.retrofit

import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.MovieSubject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by aidchow on 17-5-26.
 * retrofit  service
 */
interface APIService {


    @GET("/v2/movie/top250")
    fun loadTopW250Movies(@Query("start") start: Int,
                          @Query("count") count: Int): Call<Movie>

    //query text
    @GET("v2/movie/search")
    fun searchMovies(@Query("q") q: String): Call<Movie>

    @GET("/V2/movie/celebrity/{id}")
    fun loadCelebrity(@Path("id") id: Int): Call<Celebrity>

    @GET("/V2/movie/subject/{id}")
    fun loadMovieSubject(@Path("id") id: Int): Call<MovieSubject>

}