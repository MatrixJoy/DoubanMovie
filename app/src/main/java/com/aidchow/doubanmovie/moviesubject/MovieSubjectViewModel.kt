package com.aidchow.doubanmovie.moviesubject

import android.content.Context
import android.databinding.*
import android.graphics.Bitmap
import android.support.annotation.Nullable
import android.support.v7.graphics.Palette
import com.aidchow.doubanmovie.BR
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.data.Actor
import com.aidchow.doubanmovie.data.MovieSubject
import com.aidchow.doubanmovie.data.source.MovieSubjectDataSource
import com.aidchow.doubanmovie.data.source.MovieSubjectRepository
import com.aidchow.doubanmovie.util.Util
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget

/**
 * Created by aidchow on 17-5-29.
 */
class MovieSubjectViewModel(movieSubjectRepository: MovieSubjectRepository, var context: Context) : BaseObservable() {

    val snackBarText: ObservableField<String> = ObservableField()
    val items: ObservableList<Actor> = ObservableArrayList()
    val data: ObservableField<MovieSubject> = ObservableField()

    val errorViewVisible: ObservableBoolean = ObservableBoolean()
    val dataLoading: ObservableBoolean = ObservableBoolean()

    val mRating: ObservableField<Float> = ObservableField()
    val mMovieInfo: ObservableField<String> = ObservableField()
    val mMovieTitle: ObservableField<String> = ObservableField()
    val mMovieImageUrl: ObservableField<String> = ObservableField()
    val mMovieAverage: ObservableField<String> = ObservableField()
    val mMovieRatingCount: ObservableField<String> = ObservableField()
    val mWishOrCollectCount: ObservableField<String> = ObservableField()
    val mSummary: ObservableField<String> = ObservableField()
    val mAppbarBack: ObservableInt = ObservableInt()
    val mFabColor: ObservableInt = ObservableInt()
    val mContext: Context = context.applicationContext
    val mMovieSubjectRepository = movieSubjectRepository
    var mNavigator: MovieSubjectNavigator? = null
    fun start(movieID: Int) {
        loadMovieSubject(movieID, true)
    }

    fun setNavigator(navigator: MovieSubjectNavigator?) {
        mNavigator = navigator
    }

    fun onFragmentDestroyed() {
        data.set(null)
        items.clear()
        mNavigator = null
    }

    private fun loadMovieSubject(movieID: Int, showLoadingUI: Boolean) {
        if (showLoadingUI) {
            errorViewVisible.set(false)
            dataLoading.set(true)
        }
        mMovieSubjectRepository.loadMovieSubject(movieID, object : MovieSubjectDataSource.LoadMovieSubjectCallBack {
            override fun onMovieSubjectLoaded(movieSubject: MovieSubject) {
                errorViewVisible.set(false)
                data.set(null)
                data.set(movieSubject)
                setShowData()
                if (showLoadingUI) {
                    dataLoading.set(false)
                }
                notifyPropertyChanged(BR._all)
            }

            override fun onMovieSubjectLoadFailed(message: String) {
                errorViewVisible.set(true)
                snackBarText.set(message)
                if (showLoadingUI) {
                    dataLoading.set(false)
                }
            }
        })
    }

    /**
     * set the data show  movie subject
     */
    private fun setShowData() {
        mRating.set(data.get().rating?.stars!!.toFloat().div(10))

        val yearAndgenres = data.get()?.year + Util.parseString(data.get()?.genres.toString())
        mMovieInfo.set(mContext.getString(R.string.movie_info)
                .format(yearAndgenres, data.get().original_title,
                        Util.parseString(data.get().countries.toString())))

        mMovieTitle.set(data.get()?.title)
        mMovieImageUrl.set(data.get()?.images?.large)
        mMovieAverage.set(data.get()?.rating?.average.toString())
        mMovieRatingCount.set(mContext.getString(R.string.ratings_count).format(data.get().ratings_count))
        mWishOrCollectCount.set(mContext.getString(R.string.wish_or_collect_count).format(
                data.get().wish_count, data.get().collect_count
        ))
        Glide.with(mContext)
                .load(data.get().images?.large)
                .asBitmap()
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                        Palette.from(resource).generate { palette ->
                            val col = palette.getMutedColor(mContext.resources.getColor(R.color.colorPrimary))
                            mFabColor.set(col)
                            mAppbarBack.set(col)
                        }
                    }
                })
        mSummary.set(mContext.getString(R.string.movie_summary).format(data.get().summary))
        items.clear()
        data.get()?.casts!!.mapTo(items) { Actor(it.avatars?.medium, it.name, it.id) }
        data.get()?.directors!!.mapTo(items) { Actor(it.avatars?.medium, it.name, it.id) }
    }

    fun openMoblieUrl() {
        if (getMobileUrl() == null) {
            return
        }
        if (mNavigator != null) {
            mNavigator?.openMoblieUrl(getMobileUrl())
        }
    }

    @Nullable
    internal fun getShareUrl(): String? {
        return data.get()?.share_url
    }

    @Nullable
    internal fun getMobileUrl(): String? {
        return data.get()?.mobile_url
    }
    @Nullable
   internal fun getSnackBarText(): String? {
        return snackBarText.get()
    }
}