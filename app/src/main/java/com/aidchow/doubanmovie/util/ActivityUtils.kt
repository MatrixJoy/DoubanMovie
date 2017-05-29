package com.aidchow.doubanmovie.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by aidchow on 17-5-27.
 */
object ActivityUtils {
    fun addFragmentToActivity(frgamenManager: FragmentManager,
                              fragment: Fragment, fragmeId: Int) {

        val transaction: FragmentTransaction = frgamenManager.beginTransaction()
        transaction.add(fragmeId, fragment)
        transaction.commit()

    }

    fun addFragmentToActivity(frgamenManager: FragmentManager,
                              fragment: Fragment, tag: String) {

        val transaction: FragmentTransaction = frgamenManager.beginTransaction()
        transaction.add(fragment, tag)
        transaction.commit()

    }
}