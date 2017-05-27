package com.aidchow.doubanmovie.movies

import android.content.Context
import android.databinding.*
import android.graphics.drawable.Drawable
import com.aidchow.doubanmovie.BR
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesDataSource
import com.aidchow.doubanmovie.data.source.MoviesRepository

/**
 * Created by aidchow on 17-5-27.
 */
class MoviesViewModel(repository: MoviesRepository, context: Context) : BaseObservable() {

    val items: ObservableList<Movie.SubjectsBean> = ObservableArrayList()
    val errorLabel: ObservableField<String> = ObservableField()
    val errorIconRes: ObservableField<Drawable> = ObservableField()
    val errorViewVisible: ObservableBoolean = ObservableBoolean()
    val dataLoading: ObservableBoolean = ObservableBoolean()
    val mMovieReponsitory: MoviesRepository = repository
    val mContext: Context = context.applicationContext

    internal fun onActivityDestoryed() {
        //Clear reference to avoid potential memory leaks
    }

    fun start() {

    }

    fun loadMovies(queryType: String, showLoadingUI: Boolean = true) {
        if (showLoadingUI) {
            dataLoading.set(true)
        }
        mMovieReponsitory.getMovies(queryType, object : MoviesDataSource.LoadMoviesCallBack {
            override fun onMoviesLoaded(movies: Movie) {
                if (showLoadingUI) {
                    dataLoading.set(false)
                }
                errorViewVisible.set(false)
                items.addAll(movies.subjects!!)
                notifyPropertyChanged(BR._all)
            }

            override fun onMoviesLoadFailed(message: String) {
                errorLabel.set(message)
                errorViewVisible.set(true)
                dataLoading.set(false)
            }
        })
    }
}