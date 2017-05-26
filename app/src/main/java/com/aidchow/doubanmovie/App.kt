package com.aidchow.doubanmovie

import android.app.Application
import android.content.Context

/**
 * Created by aidchow on 17-5-26.
 */

class App : Application() {
    object C {
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        C.context = applicationContext
    }

    companion object {
        fun getContext(): Context {
            return C.context
        }
    }
}