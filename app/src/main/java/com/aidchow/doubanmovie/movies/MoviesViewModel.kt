package com.aidchow.doubanmovie.movies

import android.content.Context
import android.databinding.*
import com.aidchow.doubanmovie.BR
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesDataSource
import com.aidchow.doubanmovie.data.source.MoviesRepository

/**
 * Created by aidchow on 17-5-27.
 */
class MoviesViewModel(repository: MoviesRepository, context: Context) : BaseObservable() {

    val snackBarText: ObservableField<String> = ObservableField()
    val items: ObservableList<Movie.SubjectsBean> = ObservableArrayList()
    val errorViewVisible: ObservableBoolean = ObservableBoolean()
    val dataLoading: ObservableBoolean = ObservableBoolean()
    val mMovieRepository: MoviesRepository = repository
    val mContext: Context = context.applicationContext

    val loadMoreComplete: ObservableBoolean = ObservableBoolean()
    val loadMoreEnd: ObservableBoolean = ObservableBoolean()
    val loadMoreError: ObservableBoolean = ObservableBoolean()

    var mNavigator: MoviesNavigator? = null
    internal fun onActivityDestroyed() {
        //Clear reference to avoid potential memory leaks
        START.start = 10
    }

    private companion object START {
        var start: Int = 50
    }

    fun start() {
        loadMovies(0, true)
    }

    fun setNavigator(navigator: MoviesNavigator?) {
        mNavigator = navigator
    }

    fun openSearchMovies() {
        if (mNavigator != null) {
            mNavigator?.onSearchClick()
        }
    }


    fun loadMovies(start: Int, showLoadingUI: Boolean, loadMore: Boolean = false) {
        if (showLoadingUI) {
            dataLoading.set(true)
        }
        mMovieRepository.loadTop250Movies(start, 50, callBack = object : MoviesDataSource.LoadMoviesCallBack {
            override fun onMoviesLoaded(movies: Movie) {
                if (showLoadingUI) {
                    dataLoading.set(false)
                }

                errorViewVisible.set(false)
                items.clear()
                items.addAll(movies.subjects!!)
                if (loadMore) {
                    if (start == movies.total) {
                        loadMoreEnd.set(true)
                    } else {
                        loadMoreComplete.set(true)
                    }
                }
                notifyPropertyChanged(BR._all)
            }

            override fun onMoviesLoadFailed(message: String) {
                if (showLoadingUI) {
                    dataLoading.set(false)
                }
                if (loadMore) {
                    loadMoreError.set(true)
                } else {
                    errorViewVisible.set(true)
                }
                snackBarText.set(message)
            }
        })
    }

    fun loadMoreMovies() {
        loadMovies(start, false, true)
        start += 50
    }


    fun getSnackBarText(): String {
        return snackBarText.get()
    }

    fun getLoadMoreComplete(): Boolean {
        return loadMoreComplete.get()
    }

    fun getLoadMoreEnd(): Boolean {
        return loadMoreEnd.get()
    }

    fun getLoadMoreError(): Boolean {
        return loadMoreError.get()
    }

    fun getStatuBarHeight(): Int {
        var result = 0
        val resourceId = mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = mContext.resources.getDimensionPixelSize(resourceId)
        }

        return (result)
    }
}






