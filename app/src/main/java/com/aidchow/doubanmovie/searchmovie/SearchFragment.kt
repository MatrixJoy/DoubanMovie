package com.aidchow.doubanmovie.searchmovie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.adapter.MoviesAdapter
import com.aidchow.doubanmovie.data.Movie
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.data.source.remote.MoviesRemoteDataSource
import com.aidchow.doubanmovie.databinding.FragmentSearchMoviesBinding
import com.aidchow.doubanmovie.movies.MainActivity
import kotlinx.android.synthetic.main.fragment_search_movies.*

/**
 * Created by aidchow on 17-5-29.
 */
class SearchFragment : Fragment() {
    private var mSearchBinding: FragmentSearchMoviesBinding? = null
    private var mSearchViewModel: SearchViewModel? = null
    private var queryText: String? = null

    companion object {
        fun newInstance(): SearchFragment {

            return SearchFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val ab: ActionBar? = (activity as AppCompatActivity).supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

        val adapter = MoviesAdapter(ArrayList<Movie.SubjectsBean>(0),
                R.layout.movies_item,
                MoviesRepository.getInstance(MoviesRemoteDataSource.instance),
                activity as MainActivity)
        search_recycler_view.layoutManager = LinearLayoutManager(activity)
        search_recycler_view.adapter = adapter

        search_view.onActionViewExpanded()
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.data.clear()
                mSearchViewModel?.start(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.data.clear()
                mSearchViewModel?.start(newText!!)
                queryText = newText
                return true
            }
        })
        search_view.setOnCloseListener {
            adapter.data.clear()
            true
        }
        tv_reload.setOnClickListener { mSearchViewModel?.start(queryText!!) }
    }

    override fun onDestroy() {
        mSearchViewModel?.onFragmentDestroy()
        super.onDestroy()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mSearchBinding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        mSearchBinding?.viewModel = mSearchViewModel
        setHasOptionsMenu(true)
        val root = mSearchBinding?.root
        return root
    }

    fun setViewModel(searchViewModel: SearchViewModel) {
        mSearchViewModel = searchViewModel
    }
}