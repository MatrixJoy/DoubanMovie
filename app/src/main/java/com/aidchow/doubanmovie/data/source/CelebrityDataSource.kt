package com.aidchow.doubanmovie.data.source

import com.aidchow.doubanmovie.data.Celebrity

/**
 * Created by aidchow on 17-5-29.
 */
interface CelebrityDataSource {
    interface LoadCelebrityCallBack {
        fun onCelebrityLoaded(celebrity: Celebrity)

        fun onCelebrityLoadFailed(message: String)
    }

    fun loadCelebrity(starId: Int, callBack: CelebrityDataSource.LoadCelebrityCallBack)

}