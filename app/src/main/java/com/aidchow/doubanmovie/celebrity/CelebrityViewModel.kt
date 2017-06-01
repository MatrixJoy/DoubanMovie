package com.aidchow.doubanmovie.celebrity

import android.content.Context
import android.databinding.*
import android.graphics.Bitmap
import android.support.annotation.Nullable
import android.support.v7.graphics.Palette
import com.aidchow.doubanmovie.BR
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.data.source.CelebrityDataSource
import com.aidchow.doubanmovie.data.source.CelebrityRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget

/**
 * Created by aidchow on 17-5-29.
 */
class CelebrityViewModel(celebrityRepository: CelebrityRepository, var context: Context) : BaseObservable() {

    val snackBarText: ObservableField<String> = ObservableField()
    val items: ObservableList<Celebrity.WorksBean> = ObservableArrayList()

    val data: ObservableField<Celebrity> = ObservableField()

    val errorViewVisible: ObservableBoolean = ObservableBoolean()
    val dataLoading: ObservableBoolean = ObservableBoolean()

    val mActorImageUrl: ObservableField<String> = ObservableField()
    val mActorName: ObservableField<String> = ObservableField()
    val mActorInfo: ObservableField<String> = ObservableField()
    val mAppbarBack: ObservableInt = ObservableInt()
    val mFabColor: ObservableInt = ObservableInt()
    val mContext: Context = context.applicationContext
    val mCelebrityRepository = celebrityRepository
    var mNavigator: CelebrityNavigator? = null
    fun start(actorID: Int) {
        loadCelebrity(actorID, true)
    }

    fun setNavigator(navigator: CelebrityNavigator?) {
        mNavigator = navigator
    }

    fun onFragmentDestroyed() {
        data.set(null)
        items.clear()
        mNavigator = null
    }

    private fun loadCelebrity(actorID: Int, showLoadingUI: Boolean) {
        if (showLoadingUI) {
            errorViewVisible.set(false)
            dataLoading.set(true)
        }
        mCelebrityRepository.loadCelebrity(actorID, object : CelebrityDataSource.LoadCelebrityCallBack {

            override fun onCelebrityLoaded(celebrity: Celebrity) {
                errorViewVisible.set(false)
                data.set(null)
                data.set(celebrity)
                setShowData()
                if (showLoadingUI) {
                    dataLoading.set(false)
                }
                notifyPropertyChanged(BR._all)
            }

            override fun onCelebrityLoadFailed(message: String) {
                errorViewVisible.set(true)
                snackBarText.set(message)
                if (showLoadingUI) {
                    dataLoading.set(false)
                }
            }
        })
    }

    /**
     * set the data show  celebrity
     */
    private fun setShowData() {

        Glide.with(mContext)
                .load(data.get().avatars?.large)
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
        mActorImageUrl.set(data.get().avatars?.large)
        mActorName.set(data.get().name)
        mActorInfo.set(mContext.getString(R.string.actor_info).format(data.get().name,
                data.get().gender, data.get().name_en, data.get().born_place))
        items.clear()
        items.addAll(data.get()?.works!!)
    }

    fun openActorMobileUrl() {
        if (getMobileUrl() == null) {
            return
        }
        if (mNavigator != null) {
            mNavigator?.openActorMobileUrl(getMobileUrl())
        }
    }

    @Nullable
    internal fun getShareUrl(): String? {
        return data.get()?.mobile_url
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