package com.aidchow.doubanmovie.data.source

import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.MovieSubject

/**
 * Created by aidchow on 17-5-27.
 *  Concrete implementation to load data from the data sources into a cache.
 */
class MoviesRepository private constructor(moviesRemoteDataSource: MoviesDataSource) : MoviesDataSource {


    private val mMoviesRemoteDataSource: MoviesDataSource = moviesRemoteDataSource
    internal var cacheMovie: MutableMap<String, Movie>? = null
    internal var cacheMovieSubject: MutableMap<Int, MovieSubject>? = null
    internal var cacheCelebrity: MutableMap<Int, Celebrity>? = null

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
     * [MoviesDataSource.LoadCelebrityCallBack] when the data loaded to call back
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
     * [MoviesDataSource.LoadCelebrityCallBack] when the data loaded to call back
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

    /**
     * load Movie Subject from cache, remote data which is available first
     *@param movieId the movie's id
     * [MoviesDataSource.LoadMovieSubjectCallBack] when the data loaded to call back
     */
    override fun loadMovieSubject(movieId: Int, callBack: MoviesDataSource.LoadMovieSubjectCallBack) {
        if (cacheMovieSubject != null) {
            if (cacheMovieSubject?.get(movieId) != null) {
                callBack.onMovieSubjectLoaded(cacheMovieSubject?.get(movieId)!!)
                return
            }
        }
        mMoviesRemoteDataSource.loadMovieSubject(movieId, object : MoviesDataSource.LoadMovieSubjectCallBack {
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

    /**
     * load Celebrity from cache, remote data which is available first
     *@param starId the actor's id
     * [MoviesDataSource.LoadCelebrityCallBack] when the data loaded to call back
     */
    override fun loadCelebrity(starId: Int, callBack: MoviesDataSource.LoadCelebrityCallBack) {
        if (cacheCelebrity != null) {
            if (cacheCelebrity?.get(starId) != null) {
                callBack.onCelebrityLoaded(cacheCelebrity?.get(starId)!!)
                return
            }
        }
        mMoviesRemoteDataSource.loadCelebrity(starId, object : MoviesDataSource.LoadCelebrityCallBack {
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