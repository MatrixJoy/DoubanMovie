package com.aidchow.doubanmovie.movies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.aidchow.doubanmovie.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(viewpager)

    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            var frag: Fragment? = null
            when (position) {
                0 -> frag = MoviesFrag.newInstance("大话西游")
                1 -> frag = MoviesFrag.newInstance("环太平洋")
            }
            return frag!!
        }

        override fun getPageTitle(position: Int): CharSequence {
            val res = resources
            return res.getStringArray(R.array.tabsTitle)[position]
        }

    }

}
