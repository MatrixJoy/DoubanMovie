package com.aidchow.doubanmovie.util

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by aidchow on 17-5-29.
 */
object SnackBarUtils {
    fun showSnackBar(v: View?, snackbarText: String?) {
        if (v == null || snackbarText == null) {
            return
        }
        Snackbar.make(v, snackbarText, Snackbar.LENGTH_LONG).show()
    }
}