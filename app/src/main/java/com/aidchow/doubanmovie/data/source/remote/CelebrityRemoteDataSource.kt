package com.aidchow.doubanmovie.data.source.remote

import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.data.source.CelebrityDataSource
import com.aidchow.doubanmovie.retrofit.API
import com.aidchow.doubanmovie.retrofit.APIService
import com.aidchow.doubanmovie.retrofit.BaseRetrofit
import com.aidchow.doubanmovie.retrofit.CustemCallBack
import retrofit2.Call
import retrofit2.Response

/**
 * Created by aidchow on 17-5-29.
 */
class CelebrityRemoteDataSource private constructor() : CelebrityDataSource {
    companion object {
        private var INSTANCE: CelebrityRemoteDataSource? = null
        val instance: CelebrityRemoteDataSource
            get() {
                if (INSTANCE == null) {
                    INSTANCE = CelebrityRemoteDataSource()
                }
                return INSTANCE as CelebrityRemoteDataSource
            }
    }

    override fun loadCelebrity(starId: Int, callBack: CelebrityDataSource.LoadCelebrityCallBack) {
        val call: Call<Celebrity> = BaseRetrofit.retrofit(API.BASE_URL).create(APIService::class.java)
                .loadCelebrity(starId)
        call.enqueue(object : CustemCallBack<Celebrity>() {
            override fun onSuccess(response: Response<Celebrity>?) {
                callBack.onCelebrityLoaded(response?.body()!!)
            }

            override fun onFail(message: String) {
                callBack.onCelebrityLoadFailed(message)
            }
        })
    }
}