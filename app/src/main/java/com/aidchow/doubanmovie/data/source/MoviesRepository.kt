package com.aidchow.doubanmovie.data.source

import com.aidchow.doubanmovie.data.Movie

/**
 * Created by aidchow on 17-5-27.
 *  Concrete implementation to load Movie from the data sources into a cache.
 */
class MoviesRepository private constructor(moviesRemoteDataSource: MoviesDataSource) : MoviesDataSource {


    private val mMoviesRemoteDataSource: MoviesDataSource = moviesRemoteDataSource
    internal var cacheMovie: MutableMap<String, Movie>? = null


    companion object {
        private var INSTANCE: MoviesRepository? = null


        fun getInstance(moviesRemoteDataSource: MoviesDataSource): MoviesRepository {
            if (INSTANCE == null) {
                INSTANCE = MoviesRepository(moviesRemoteDataSource)
            }
            return INSTANCE as MoviesRepository
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    /**
     * load Top250 Movies from cache, remote data which is available first
     *@param start the data start position
     * @param count the load data count
     * [MoviesDataSource.LoadMoviesCallBack] when the data loaded to call back
     */
    override fun loadTop250Movies(start: Int, count: Int, callBack: MoviesDataSource.LoadMoviesCallBack) {
        if (cacheMovie != null) {
            if (cacheMovie?.get(start.toString()) != null) {
                callBack.onMoviesLoaded(cacheMovie?.get(start.toString())!!)
                return
            }

        }
        mMoviesRemoteDataSource.loadTop250Movies(start, count, object : MoviesDataSource.LoadMoviesCallBack {
            override fun onMoviesLoaded(movies: Movie) {
                if (cacheMovie == null) {
                    cacheMovie = LinkedHashMap()
                }
                cacheMovie?.put(start.toString(), movies)
                callBack.onMoviesLoaded(movies)
            }

            override fun onMoviesLoadFailed(message: String) {
                callBack.onMoviesLoadFailed(message)
            }
        })
    }

    /**
     * search Movies from cache, remote data which is available first
     *@param queryText the query text
     * [MoviesDataSource.LoadMoviesCallBack] when the data loaded to call back
     */
    override fun searchMovies(queryText: String, callBack: MoviesDataSource.LoadMoviesCallBack) {
        if (cacheMovie != null) {
            if (cacheMovie?.get(queryText) != null) {
                callBack.onMoviesLoaded(cacheMovie?.get(queryText)!!)
                return
            }

        }
        mMoviesRemoteDataSource.searchMovies(queryText, object : MoviesDataSource.LoadMoviesCallBack {
            override fun onMoviesLoaded(movies: Movie) {
                if (cacheMovie == null) {
                    cacheMovie = LinkedHashMap()
                }
                cacheMovie?.put(queryText, movies)
                callBack.onMoviesLoaded(movies)
            }

            override fun onMoviesLoadFailed(message: String) {
                callBack.onMoviesLoadFailed(message)
            }
        })
    }


}