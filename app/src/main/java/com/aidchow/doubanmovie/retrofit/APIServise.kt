package com.aidchow.doubanmovie.retrofit

import com.aidchow.doubanmovie.data.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by aidchow on 17-5-26.
 */
interface APIServise {
    @GET("/v2/movie/{type}")
    fun getTop250(@Path("type") type: String): Call<Movies>
}