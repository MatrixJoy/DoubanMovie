package com.aidchow.doubanmovie.celebrity

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.movies.MovieItemNavigator
import com.aidchow.doubanmovie.util.Util
import java.lang.ref.WeakReference

/**
 * Created by aidchow on 17-5-27.
 */
class CelebrityItemViewModel(context: Context) : BaseObservable() {
    val mContext: Context = context.applicationContext
    var mNavigator: WeakReference<MovieItemNavigator>? = null
    val data: ObservableField<Celebrity.WorksBean> = ObservableField()
    val roles: ObservableField<String> = ObservableField()
    fun setNavigator(navigator: MovieItemNavigator?) {
        mNavigator = WeakReference<MovieItemNavigator>(navigator)
    }

    fun setCelebrityWork(celebrity: Celebrity.WorksBean) {
        data.set(celebrity)
    }

    fun movieClicked() {
        if (getMovieId() == null) {
            return
        }
        if (mNavigator != null && mNavigator?.get() != null) {
            mNavigator?.get()?.openMovieSubject(getMovieId()!!)
        }
    }

    @Bindable
    fun getRating(): Float {
        val start: Float = data.get().subject?.rating?.stars?.toFloat()!! / 10
        return start
    }

    @Bindable
    fun getRatinText(): String {
        return data.get().subject?.rating?.average.toString()
    }

    @Bindable
    fun getMovieTitle(): String? {
        return data.get().subject?.title
    }

    @Bindable
    fun getMovieImageUrl(): String? {
        return data.get().subject?.images?.medium
    }

    @Bindable
    fun getroles(): String {
        roles.set(mContext.getString(R.string.roles_and_genres)
                .format(Util.parseString(data.get().roles.toString()),
                        Util.parseString(data.get().subject?.genres.toString())))
        return roles.get()
    }

    fun getMovieId(): Int? {
        return data.get()?.subject?.id?.toInt()
    }
}