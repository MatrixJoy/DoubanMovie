package com.aidchow.doubanmovie.movies

import android.content.Context
import android.databinding.Bindable
import android.databinding.ObservableField
import com.aidchow.doubanmovie.MovieViewModel
import com.aidchow.doubanmovie.data.source.MoviesRepository
import java.lang.ref.WeakReference

/**
 * Created by aidchow on 17-5-27.
 */
class MovieItemViewModel(context: Context, moviesRepository: MoviesRepository)
    : MovieViewModel(context, moviesRepository) {
    val positionObservable: ObservableField<String> = ObservableField()

    var mNavigator: WeakReference<MovieItemNavigator>? = null

    fun setNavigator(navigator: MovieItemNavigator?) {
        mNavigator = WeakReference<MovieItemNavigator>(navigator)
    }

    fun setPosition(poi: Int) {
        positionObservable.set(poi.toString())
    }

    @Bindable
    fun getPoi(): String {
        return positionObservable.get()
    }

    fun movieClicked() {
        if (mNavigator != null && mNavigator?.get() != null) {
            mNavigator?.get()?.openMovieSubject(getMovieId())
        }
    }
}