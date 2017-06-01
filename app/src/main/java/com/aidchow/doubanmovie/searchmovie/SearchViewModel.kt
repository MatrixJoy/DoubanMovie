package com.aidchow.doubanmovie.searchmovie

import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.aidchow.doubanmovie.MovieViewModel
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesDataSource
import com.aidchow.doubanmovie.data.source.MoviesRepository

/**
 * Created by aidchow on 17-6-1.
 */
class SearchViewModel(context: Context, moviesRepository: MoviesRepository) : MovieViewModel(context, moviesRepository) {
    val items:ObservableList<Movie.SubjectsBean> = ObservableArrayList()
    val mMovieRepository:MoviesRepository=moviesRepository
    val errorViewVisible: ObservableBoolean = ObservableBoolean()
    val dataLoading: ObservableBoolean = ObservableBoolean()
    val empty:ObservableBoolean = ObservableBoolean()

    val mContext:Context = context.applicationContext


    fun start(query:String){
        if (query.isEmpty()){
            return
        }
        searchMovies(query,true)
    }
    fun onFragmentDestroy(){
        items.clear()
    }

    private fun searchMovies(query:String,showLoadingUI:Boolean){
        if (showLoadingUI){
            dataLoading.set(true)
            errorViewVisible.set(false)
            empty.set(false)
        }
        mMovieRepository.searchMovies(query,object :MoviesDataSource.LoadMoviesCallBack{
            override fun onMoviesLoaded(movies: Movie) {
                if (movies.total==0){
                    empty.set(true)
                }
                movies.subjects?.let { items.addAll(it) }
                dataLoading.set(false)
            }

            override fun onMoviesLoadFailed(message: String) {
                dataLoading.set(false)
                errorViewVisible.set(true)
                snackBarText.set(message)
            }
        })
    }

}