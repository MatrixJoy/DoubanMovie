package com.aidchow.doubanmovie.movies

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.ViewModelHolder
import com.aidchow.doubanmovie.celebrity.CelebrityFragment
import com.aidchow.doubanmovie.celebrity.CelebrityViewModel
import com.aidchow.doubanmovie.data.source.CelebrityRepository
import com.aidchow.doubanmovie.data.source.MovieSubjectRepository
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.data.source.remote.CelebrityRemoteDataSource
import com.aidchow.doubanmovie.data.source.remote.MovieSubjectRemoteDataSource
import com.aidchow.doubanmovie.data.source.remote.MoviesRemoteDataSource
import com.aidchow.doubanmovie.moviesubject.ActorItemNavigator
import com.aidchow.doubanmovie.moviesubject.MovieSubjectViewModel
import com.aidchow.doubanmovie.searchmovie.MovieSubjectFragment
import com.aidchow.doubanmovie.searchmovie.SearchFragment
import com.aidchow.doubanmovie.searchmovie.SearchViewModel
import com.aidchow.doubanmovie.ui.AboutActivity
import com.aidchow.doubanmovie.util.ActivityUtils
import com.mikepenz.aboutlibraries.LibsBuilder
import com.mikepenz.aboutlibraries.ui.LibsSupportFragment

class MainActivity : AppCompatActivity(), MovieItemNavigator, MoviesNavigator, ActorItemNavigator,
        MoviesFragment.OnAboutMenuClickListener {

    val MOVIES_VIEWMODEL_TAG: String = "MOVIES_VIEWMODEL_TAG"
    val MOVIES_SUBJECT_VIEWMODEL_TAG: String = "MOVIES_SUBJECT_VIEWMODEL_TAG"
    val CELEBRITY_VIEWMODEL_TAG: String = "CELEBRITY_VIEWMODEL_TAG"
    val SEARCH_VIEWMODEL_TAG: String = "SEARCH_VIEWMODEL_TAG"

    private var mMovieFrgment: MoviesFragment? = null

    private var mViewModel: MoviesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = findOrCreateViewModel()

        mMovieFrgment = findMoviesFragment()

        mViewModel?.setNavigator(this)

        mMovieFrgment?.setViewModel(mViewModel!!)

    }

    private fun findMoviesFragment(): MoviesFragment? {
        var fragment: MoviesFragment? = supportFragmentManager.findFragmentById(R.id.frame_container) as? MoviesFragment
        if (fragment == null) {
            fragment = MoviesFragment.newInstance()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.frame_container)
        }
        return fragment
    }

    override fun onDestroy() {
        mViewModel?.onActivityDestroyed()
        super.onDestroy()
    }


    private fun findOrCreateViewModel(): MoviesViewModel {
        val retainedViewModel: ViewModelHolder<*>? = supportFragmentManager
                .findFragmentByTag(MOVIES_VIEWMODEL_TAG) as? ViewModelHolder<*>
        if (retainedViewModel != null) {
            return retainedViewModel.mViewModel as MoviesViewModel
        } else {
            val viewModel: MoviesViewModel = MoviesViewModel(
                    MoviesRepository.getInstance(MoviesRemoteDataSource.instance), this)
            ActivityUtils.addFragmentToActivity(supportFragmentManager, ViewModelHolder.createContainer(viewModel)
                    , MOVIES_SUBJECT_VIEWMODEL_TAG)
            return viewModel
        }

    }

    private fun findOrCreateMovieSubjectViewModel(): MovieSubjectViewModel {
        val retainedViewModel: ViewModelHolder<*>? = supportFragmentManager
                .findFragmentByTag(MOVIES_VIEWMODEL_TAG) as? ViewModelHolder<*>
        if (retainedViewModel != null) {
            return retainedViewModel.mViewModel as MovieSubjectViewModel
        } else {
            val viewModel: MovieSubjectViewModel = MovieSubjectViewModel(
                    MovieSubjectRepository.getInstance(MovieSubjectRemoteDataSource.instance), this)
            ActivityUtils.addFragmentToActivity(supportFragmentManager, ViewModelHolder.createContainer(viewModel)
                    , MOVIES_VIEWMODEL_TAG)
            return viewModel
        }

    }

    private fun findOrCreateCelebrityViewModel(): CelebrityViewModel {
        val retainedViewModel: ViewModelHolder<*>? = supportFragmentManager
                .findFragmentByTag(CELEBRITY_VIEWMODEL_TAG) as? ViewModelHolder<*>
        if (retainedViewModel != null) {
            return retainedViewModel.mViewModel as CelebrityViewModel
        } else {
            val viewModel: CelebrityViewModel = CelebrityViewModel(
                    CelebrityRepository.getInstance(CelebrityRemoteDataSource.instance), this)
            ActivityUtils.addFragmentToActivity(supportFragmentManager, ViewModelHolder.createContainer(viewModel)
                    , CELEBRITY_VIEWMODEL_TAG)
            return viewModel
        }

    }

    private fun findOrCreateSearchViewModel(): SearchViewModel {
        val retainedViewModel: ViewModelHolder<*>? = supportFragmentManager
                .findFragmentByTag(SEARCH_VIEWMODEL_TAG) as? ViewModelHolder<*>
        if (retainedViewModel != null) {
            return retainedViewModel.mViewModel as SearchViewModel
        } else {
            val viewModel: SearchViewModel = SearchViewModel(this,
                    MoviesRepository.getInstance(MoviesRemoteDataSource.instance))
            ActivityUtils.addFragmentToActivity(supportFragmentManager, ViewModelHolder.createContainer(viewModel)
                    , SEARCH_VIEWMODEL_TAG)
            return viewModel
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home ->
                onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun openMovieSubject(movieID: Int) {
        val movieSubjectFragment = MovieSubjectFragment.newInstance(movieID)
        val movieSubjectViewModel = findOrCreateMovieSubjectViewModel()
        movieSubjectFragment.setViewModel(movieSubjectViewModel)
        val ft=supportFragmentManager.beginTransaction()

        ft.add(R.id.frame_container, movieSubjectFragment, MOVIES_SUBJECT_VIEWMODEL_TAG)
                .addToBackStack(MOVIES_SUBJECT_VIEWMODEL_TAG)
                .hide(mMovieFrgment)

        if (supportFragmentManager.findFragmentByTag(CELEBRITY_VIEWMODEL_TAG) != null) {
            ft.hide(supportFragmentManager.findFragmentByTag(CELEBRITY_VIEWMODEL_TAG))
        }
        if (supportFragmentManager.findFragmentByTag(SEARCH_VIEWMODEL_TAG) != null) {
          ft.hide(supportFragmentManager.findFragmentByTag(SEARCH_VIEWMODEL_TAG))
        }
        ft.commitAllowingStateLoss()

    }

    override fun onSearchClick() {
        val mSearchFragment = SearchFragment.newInstance()
        val searchViewModel = findOrCreateSearchViewModel()
        supportFragmentManager.beginTransaction()
                .add(R.id.frame_container, mSearchFragment, SEARCH_VIEWMODEL_TAG)
                .addToBackStack(SEARCH_VIEWMODEL_TAG)
                .hide(mMovieFrgment)
                .commitAllowingStateLoss()
        mSearchFragment.setViewModel(searchViewModel)
    }

    override fun onAboutMenuClick() {
        startActivity(Intent(this,AboutActivity::class.java))
    }

    override fun openCelebrity(actorId: Int?) {
        val mCelebrityFrgament = CelebrityFragment.newInstance(actorId)
        val celebrityViewModel = findOrCreateCelebrityViewModel()
        mCelebrityFrgament.setViewModel(celebrityViewModel)
        supportFragmentManager.beginTransaction()
                .add(R.id.frame_container, mCelebrityFrgament, CELEBRITY_VIEWMODEL_TAG)
                .addToBackStack(CELEBRITY_VIEWMODEL_TAG)
                .hide(supportFragmentManager.findFragmentByTag(MOVIES_SUBJECT_VIEWMODEL_TAG))
                .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}
