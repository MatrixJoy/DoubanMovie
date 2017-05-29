package com.aidchow.doubanmovie.movies

import android.content.Context
import android.databinding.*
import android.graphics.drawable.Drawable
import com.aidchow.doubanmovie.data.Movie
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
    val mMovieRepository: MoviesRepository = repository
    val mContext: Context = context.applicationContext

    internal fun onActivityDestoryed() {
        //Clear reference to avoid potential memory leaks
    }

    fun start() {
    }


}