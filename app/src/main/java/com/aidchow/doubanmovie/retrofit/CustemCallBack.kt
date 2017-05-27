package com.aidchow.doubanmovie.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by aidchow on 17-5-27.
 */
abstract class CustemCallBack<T> : Callback<T> {
    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        if (response != null) {
            if (response.isSuccessful) {
                onSuccess(response)
            } else {
                onFail(response.message())
            }
        }

    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        onFail(t?.message as String)
    }

    abstract fun onSuccess(response: Response<T>?)

    abstract fun onFail(message: String)
}