package com.aidchow.doubanmovie.movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.ViewModelHolder
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.data.source.remote.MoviesRemoteDataSource
import com.aidchow.doubanmovie.util.ActivityUtils
import com.aidchow.doubanmovie.util.SnackBarUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieItemNavigator {

    val MOVIES_VIEWMODEL_TAG: String = "MOVIES_VIEWMODEL_TAG"

    private lateinit var mViewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mViewModel = findOrCreateViewModel()
        var fragment: MoviesFragment? = supportFragmentManager.findFragmentById(R.id.frame_container) as? MoviesFragment
        if (fragment == null) {
            fragment = MoviesFragment.newInstance()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.frame_container)
        }

        fragment.setViewModel(mViewModel)
    }

    override fun onDestroy() {
        mViewModel.onActivityDestroyed()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
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
                    , MOVIES_VIEWMODEL_TAG)
            return viewModel
        }

    }

    override fun openMovieSubject(movieID: Int) {
        SnackBarUtils.showSnackBar(toolbar, movieID.toString())
    }
}
