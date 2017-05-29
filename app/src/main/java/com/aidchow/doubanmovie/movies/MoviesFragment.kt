package com.aidchow.doubanmovie.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.adapter.MoviesAdapter
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.data.source.remote.MoviesRemoteDataSource
import com.aidchow.doubanmovie.databinding.FragmentMoviesBinding
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * Created by aidchow on 17-5-26.
 * Movies Fragment to show the movies list
 */
class MoviesFragment : Fragment() {


    private lateinit var mMovieViewModel: MoviesViewModel
    private lateinit var mMoviesFagDataBinding: FragmentMoviesBinding

    companion object {
        fun newInstance(): MoviesFragment {
            val movFrag = MoviesFragment()
            return movFrag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setupRefreshLayout() {
        refresh_layout.setColorSchemeColors(
                ContextCompat.getColor(activity, R.color.colorPrimary),
                ContextCompat.getColor(activity, R.color.colorPrimaryDark),
                ContextCompat.getColor(activity, R.color.colorAccent)
        )
        refresh_layout.setScrollUpchild(movies_recycler_view)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRefreshLayout()
        val adapter = MoviesAdapter(ArrayList<Movie.SubjectsBean>(10),
                MoviesRepository.getInstance(MoviesRemoteDataSource.instance))
        movies_recycler_view.layoutManager = LinearLayoutManager(activity)
        movies_recycler_view.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mMoviesFagDataBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        mMoviesFagDataBinding.viewModel = mMovieViewModel
        val root = mMoviesFagDataBinding.root
        return root
    }

    fun setViewModel(viewModel: MoviesViewModel) {
        mMovieViewModel = viewModel
    }


}