package com.aidchow.doubanmovie

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField
import android.support.annotation.Nullable
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesRepository

/**
 * Created by aidchow on 17-5-29.
 */
abstract class MovieViewModel(var context: Context, var moviesRepository: MoviesRepository)
    : BaseObservable() {

    val snackBarText: ObservableField<String> = ObservableField()
    val mDirectorsObservable: ObservableField<String> = ObservableField()
    val mCastsObservable: ObservableField<String> = ObservableField()
    val mMovieObservable: ObservableField<Movie.SubjectsBean> = ObservableField()
    val mRatingObservable: ObservableField<Float> = ObservableField()

    private val mContex: Context = context.applicationContext
    private val mMoviesRepository: MoviesRepository = moviesRepository


    fun setMovie(movie: Movie.SubjectsBean) {
        mMovieObservable.set(movie)
    }

    @Bindable
    fun getMovieTitle(): String {
        return mMovieObservable.get()?.title!!
    }

    @Bindable
    fun getRating(): Float {
        val start: Float = mMovieObservable.get().rating?.stars?.toFloat()!! / 10
        mRatingObservable.set(start)
        return mRatingObservable.get()
    }

    @Bindable
    fun getRatinText(): String {
        return mMovieObservable.get().rating?.average.toString()
    }

    @Bindable
    fun getDirectors(): String {
        val directorsText: StringBuilder = StringBuilder().append("导演：")
        for (directors: Movie.SubjectsBean.DirectorsBean in mMovieObservable.get()?.directors!!) {
            directorsText.append(directors.name)
                    .append("/")

        }
        var result = directorsText.toString()
        result=result.substring(0,result.length-1)
        mDirectorsObservable.set(result)
        return mDirectorsObservable.get()
    }

    @Bindable
    fun getCasts(): String {
        val castsText: StringBuilder = StringBuilder().append( "演员：")
        for (directors: Movie.SubjectsBean.CastsBean in mMovieObservable.get()?.casts!!) {
            castsText.append(directors.name)
                    .append("/")
        }
        var result = castsText.toString()
        result =result.substring(0, result.length-1)
        mCastsObservable.set(result)
        return mCastsObservable.get()
    }
    @Bindable
    fun getMovieImageUrl():String{
        return mMovieObservable.get().images?.medium.toString()
    }


    @Nullable
    protected fun getMovieId(): Int {
        return mMovieObservable.get()?.id?.toInt()!!
    }

    fun getSnackBarText(): String {
        return snackBarText.get()
    }

}