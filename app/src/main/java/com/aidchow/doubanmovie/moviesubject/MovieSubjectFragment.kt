package com.aidchow.doubanmovie.searchmovie

import android.content.Intent
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.adapter.ActorAdapter
import com.aidchow.doubanmovie.data.Actor
import com.aidchow.doubanmovie.databinding.FragmentMovieSubjectBinding
import com.aidchow.doubanmovie.movies.MainActivity
import com.aidchow.doubanmovie.moviesubject.MovieSubjectNavigator
import com.aidchow.doubanmovie.moviesubject.MovieSubjectViewModel
import com.aidchow.doubanmovie.ui.WebViewActivity
import com.aidchow.doubanmovie.util.SnackBarUtils
import kotlinx.android.synthetic.main.fragment_movie_subject.*

/**
 * Created by aidchow on 17-5-29.
 */
class MovieSubjectFragment : Fragment(), MovieSubjectNavigator {


    private val TAG: String = "MovieSubjectFragment"

    private var mMovieSubjectBinding: FragmentMovieSubjectBinding? = null
    private var mMovieSubjectViewModel: MovieSubjectViewModel? = null
    private var movieID: Int = 0
    private var mSnackBarCallBack: Observable.OnPropertyChangedCallback? = null
    companion object {
        fun newInstance(movieID: Int?): MovieSubjectFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt("movie_id", movieID!!)
            bundle.putBoolean("reload", false)
            val fragment: MovieSubjectFragment = MovieSubjectFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieID = arguments.getInt("movie_id")
    }

    override fun onResume() {
        super.onResume()
        if (arguments?.getBoolean("reload")!!) {
            return
        }
        if (movieID != 0) {
            mMovieSubjectViewModel?.start(movieID)
        }
    }

    override fun onDestroy() {
        if (mSnackBarCallBack != null) {
            mMovieSubjectViewModel?.snackBarText?.removeOnPropertyChangedCallback(mSnackBarCallBack)
        }
        mMovieSubjectViewModel?.onFragmentDestroyed()
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val ab: ActionBar? = (activity as AppCompatActivity).supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

        actor_recycler_view.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter: ActorAdapter = ActorAdapter(R.layout.actor_item, activity as MainActivity, ArrayList<Actor>(0))
        actor_recycler_view.adapter = adapter

        tv_reload.setOnClickListener { mMovieSubjectViewModel?.start(movieID) }

        mSnackBarCallBack = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                SnackBarUtils.showSnackBar(view, mMovieSubjectViewModel?.getSnackBarText())
            }
        }
        mMovieSubjectViewModel?.snackBarText?.addOnPropertyChangedCallback(mSnackBarCallBack)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.movie_subjiect_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_share -> if (mMovieSubjectViewModel?.getShareUrl() != null)
                startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND)
                        .setType("text/*").putExtra(Intent.EXTRA_TEXT, mMovieSubjectViewModel?.getShareUrl()),
                        activity.getString(R.string.share_to)))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mMovieSubjectBinding = FragmentMovieSubjectBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        mMovieSubjectBinding?.viewModel = mMovieSubjectViewModel
        val root = mMovieSubjectBinding?.root
        return root
    }

    fun setViewModel(movieSubjectViewModel: MovieSubjectViewModel) {
        mMovieSubjectViewModel = movieSubjectViewModel
        movieSubjectViewModel.setNavigator(this)
    }


    override fun openMoblieUrl(moblieUrl: String?) {
        WebViewActivity.startActvity(activity, moblieUrl)
    }


}