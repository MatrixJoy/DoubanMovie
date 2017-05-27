package com.aidchow.doubanmovie.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.ViewModelHolder
import com.aidchow.doubanmovie.data.source.MoviesRepository
import com.aidchow.doubanmovie.data.source.remote.MoviesRemoteDataSource
import com.aidchow.doubanmovie.util.ActivityUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val MOVIES_VIEWMODEL_TAG: String = "MOVIES_VIEWMODEL_TAG"

    private lateinit var mViewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)

        mViewModel = findOrCreateViewModel()

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = viewPagerAdapter
        val top250: MoviesFrag = MoviesFrag.newInstance("top250")
        top250.setViewModel(mViewModel)
        val usBox: MoviesFrag = MoviesFrag.newInstance("us_box")
        usBox.setViewModel(mViewModel)
        viewPagerAdapter.addFragment(top250)
        viewPagerAdapter.addFragment(usBox)
        viewPagerAdapter.notifyDataSetChanged()
        tab_layout.setupWithViewPager(viewpager)

    }

    private fun findOrCreateViewModel(): MoviesViewModel {
        val retainedViewModel = supportFragmentManager
                .findFragmentByTag(MOVIES_VIEWMODEL_TAG)

        if (retainedViewModel != null) {
            retainedViewModel as ViewModelHolder<*>
            return retainedViewModel.mViewModel as MoviesViewModel
        } else {
            val viewModel: MoviesViewModel = MoviesViewModel(
                    MoviesRepository.getInstance(MoviesRemoteDataSource.instance), this)
            ActivityUtils.addFragmentToActivity(supportFragmentManager, ViewModelHolder.createContainer(viewModel)
                    , MOVIES_VIEWMODEL_TAG)
            return viewModel
        }

    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        var fragmentList: ArrayList<MoviesFrag> = ArrayList()

        fun addFragment(fragment: MoviesFrag) {
            fragmentList.add(fragment)
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {

            return fragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence {
            val res = resources
            return res.getStringArray(R.array.tabsTitle)[position]
        }

    }

}
