package com.aidchow.doubanmovie.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aidchow.doubanmovie.R

/**
 * Created by aidchow on 17-5-26.
 */
class MoviesFrag : Fragment() {

    private object MovieType {
        const val MOVIE_TYPE: String = "type"
    }


    companion object {
        @JvmStatic fun newInstance(type: String): MoviesFrag {
            val bundle = Bundle()
            bundle.putString(MovieType.MOVIE_TYPE, type)
            val movFrag = MoviesFrag()
            movFrag.arguments = bundle
            return movFrag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_movies, container, false)
        return view
    }


}