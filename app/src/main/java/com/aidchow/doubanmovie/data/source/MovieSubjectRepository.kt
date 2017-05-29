package com.aidchow.doubanmovie.data.source

import com.aidchow.doubanmovie.data.MovieSubject

/**
 * Created by aidchow on 17-5-29.
 * Concrete implementation to load MovieSubject from the data sources into a cache.
 */
class MovieSubjectRepository private constructor(var mMovieSubjectDataSource: MovieSubjectDataSource) : MovieSubjectDataSource {
    internal var cacheMovieSubject: MutableMap<Int, MovieSubject>? = null

    companion object {
        private var INSTANCE: MovieSubjectRepository? = null


        fun getInstance(movieSubjectDataSource: MovieSubjectDataSource): MovieSubjectRepository {
            if (INSTANCE == null) {
                INSTANCE = MovieSubjectRepository(movieSubjectDataSource)
            }
            return INSTANCE as MovieSubjectRepository
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    /**
     *
     * load Movie Subject from cache, remote data which is available first
     * @param movieId the movie's id
     * [MovieSubjectDataSource.LoadMovieSubjectCallBack] when the data loaded to call back
     */
    override fun loadMovieSubject(movieId: Int, callBack: MovieSubjectDataSource.LoadMovieSubjectCallBack) {
        if (cacheMovieSubject != null) {
            if (cacheMovieSubject?.get(movieId) != null) {
                callBack.onMovieSubjectLoaded(cacheMovieSubject?.get(movieId)!!)
                return
            }
        }
        mMovieSubjectDataSource.loadMovieSubject(movieId, object : MovieSubjectDataSource.LoadMovieSubjectCallBack {
            override fun onMovieSubjectLoaded(movieSubject: MovieSubject) {
                if (cacheMovieSubject == null) {
                    cacheMovieSubject = LinkedHashMap()
                }
                cacheMovieSubject?.put(movieId, movieSubject)
                callBack.onMovieSubjectLoaded(movieSubject)
            }

            override fun onMovieSubjectLoadFailed(message: String) {
                callBack.onMovieSubjectLoadFailed(message)
            }
        })
    }
}