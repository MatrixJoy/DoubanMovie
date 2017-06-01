package com.aidchow.doubanmovie.data.source

import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.data.source.remote.CelebrityRemoteDataSource

/**
 * Created by aidchow on 17-5-29.
 * Concrete implementation to load Celebrity from the data sources into a cache.
 */
class CelebrityRepository private constructor(var mCelebrityDataSource: CelebrityDataSource) : CelebrityDataSource {

    internal var cacheCelebrity: MutableMap<Int, Celebrity>? = null

    companion object {
        private var INSTANCE: CelebrityRepository? = null


        fun getInstance(celebrityDataSource: CelebrityRemoteDataSource): CelebrityRepository {
            if (INSTANCE == null) {
                INSTANCE = CelebrityRepository(celebrityDataSource)
            }
            return INSTANCE as CelebrityRepository
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    /**
     * load Celebrity from cache, remote data which is available first
     * @param starId the actor's id
     * [CelebrityDataSource.LoadCelebrityCallBack] when the data loaded to call back
     */
    override fun loadCelebrity(starId: Int, callBack: CelebrityDataSource.LoadCelebrityCallBack) {
        if (cacheCelebrity != null) {
            if (cacheCelebrity?.get(starId) != null) {
                callBack.onCelebrityLoaded(cacheCelebrity?.get(starId)!!)
                return
            }
        }
        mCelebrityDataSource.loadCelebrity(starId, object : CelebrityDataSource.LoadCelebrityCallBack {
            override fun onCelebrityLoaded(celebrity: Celebrity) {
                if (cacheCelebrity == null) {
                    cacheCelebrity = LinkedHashMap()
                }
                cacheCelebrity?.put(starId, celebrity)
                callBack.onCelebrityLoaded(celebrity)
            }

            override fun onCelebrityLoadFailed(message: String) {
                callBack.onCelebrityLoadFailed(message)
            }
        })
    }
}