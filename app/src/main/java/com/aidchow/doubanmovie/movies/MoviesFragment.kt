package com.aidchow.doubanmovie.movies

import android.content.Context
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.adapter.MoviesAdapter
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.data.source.remote.MoviesRemoteDataSource
import com.aidchow.doubanmovie.databinding.FragmentMoviesBinding
import com.aidchow.doubanmovie.util.SnackBarUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import java.lang.RuntimeException

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
    private var act: AppCompatActivity? = null
    private var listener: OnAboutMenuClickListener? = null

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
        act = activity as AppCompatActivity
        act?.setSupportActionBar(toolbar)
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
                }
                if (mMovieViewModel?.getLoadMoreEnd()!!) {
                    adapter.loadMoreEnd()
                    mMovieViewModel?.loadMoreEnd?.set(false)

                }
                if (mMovieViewModel?.getLoadMoreError()!!) {
                    adapter.loadMoreFail()
                    mMovieViewModel?.loadMoreError?.set(false)
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


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnAboutMenuClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.about) {
            if (listener != null) {
                listener?.onAboutMenuClick()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mMoviesFagDataBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        mMoviesFagDataBinding?.viewModel = mMovieViewModel
        setHasOptionsMenu(true)
        val root = mMoviesFagDataBinding?.root
        return root
    }


    fun setViewModel(viewModel: MoviesViewModel) {
        mMovieViewModel = viewModel
    }

    override fun onLoadMoreRequested() {
        mMovieViewModel?.loadMoreMovies()
    }

    interface OnAboutMenuClickListener {
        fun onAboutMenuClick()
    }
}