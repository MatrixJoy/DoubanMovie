package com.aidchow.doubanmovie.movies

import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.adapter.MoviesAdapter
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.data.source.remote.MoviesRemoteDataSource
import com.aidchow.doubanmovie.databinding.FragmentMoviesBinding
import com.aidchow.doubanmovie.util.SnackBarUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * Created by aidchow on 17-5-26.
 * Movies Fragment to show the movies list
 */
class MoviesFragment : Fragment(), BaseQuickAdapter.RequestLoadMoreListener {

    private val TAG: String = "MoviesFragment"
    private var mMovieViewModel: MoviesViewModel? = null
    private var mMoviesFagDataBinding: FragmentMoviesBinding? = null
    private var mSnackBarCallBack: Observable.OnPropertyChangedCallback? = null
    private var mLoadMoreCallBack: Observable.OnPropertyChangedCallback? = null

    companion object {
        fun newInstance(): MoviesFragment {
            val bundle = Bundle()
            val movFrag = MoviesFragment()
            bundle.putBoolean("restore", false)
            movFrag.arguments = bundle
            return movFrag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        if (!arguments.getBoolean("restore"))
            mMovieViewModel?.start()
    }

    override fun onPause() {
        super.onPause()
        arguments.putBoolean("restore", true)
    }

    override fun onDestroy() {
        if (mSnackBarCallBack != null) {
            mMovieViewModel?.snackBarText?.removeOnPropertyChangedCallback(mSnackBarCallBack)
        }
        if (mLoadMoreCallBack != null) {
            mMovieViewModel?.loadMoreComplete?.removeOnPropertyChangedCallback(mLoadMoreCallBack)
            mMovieViewModel?.loadMoreError?.removeOnPropertyChangedCallback(mLoadMoreCallBack)
            mMovieViewModel?.loadMoreEnd?.removeOnPropertyChangedCallback(mLoadMoreCallBack)
        }
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = MoviesAdapter(ArrayList<Movie.SubjectsBean>(0),
                R.layout.movies_item,
                MoviesRepository.getInstance(MoviesRemoteDataSource.instance),
                activity as MainActivity)
        movies_recycler_view.layoutManager = LinearLayoutManager(activity)
        movies_recycler_view.adapter = adapter
        adapter.setOnLoadMoreListener(this, movies_recycler_view)

        mSnackBarCallBack = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                SnackBarUtils.showSnackBar(view, mMovieViewModel?.getSnackBarText())
            }
        }
        mLoadMoreCallBack = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                if (mMovieViewModel?.getLoadMoreComplete()!!) {
                    adapter.loadMoreComplete()
                    mMovieViewModel?.loadMoreComplete?.set(false)
                    Log.d(TAG, "加载完成")
                }
                if (mMovieViewModel?.getLoadMoreEnd()!!) {
                    adapter.loadMoreEnd()
                    mMovieViewModel?.loadMoreEnd?.set(false)
                    Log.d(TAG, "加载结束")

                }
                if (mMovieViewModel?.getLoadMoreError()!!) {
                    adapter.loadMoreFail()
                    mMovieViewModel?.loadMoreError?.set(false)
                    Log.d(TAG, "加载失败")
                }
            }
        }
        mMovieViewModel?.snackBarText?.addOnPropertyChangedCallback(mSnackBarCallBack)
        mMovieViewModel?.loadMoreComplete?.addOnPropertyChangedCallback(mLoadMoreCallBack)
        mMovieViewModel?.loadMoreError?.addOnPropertyChangedCallback(mLoadMoreCallBack)
        mMovieViewModel?.loadMoreEnd?.addOnPropertyChangedCallback(mLoadMoreCallBack)

        //if something happen　reload the data
        tv_reload.setOnClickListener { mMovieViewModel?.start() }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mMoviesFagDataBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        mMoviesFagDataBinding?.viewModel = mMovieViewModel
        val root = mMoviesFagDataBinding?.root
        return root
    }

    fun setViewModel(viewModel: MoviesViewModel) {
        mMovieViewModel = viewModel
    }

    override fun onLoadMoreRequested() {
        Log.d(TAG, "onLoadMoreRequested")
        mMovieViewModel?.loadMoreMovies()
    }
}