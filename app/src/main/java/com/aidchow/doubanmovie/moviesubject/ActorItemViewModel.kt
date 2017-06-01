package com.aidchow.doubanmovie.moviesubject

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField
import com.aidchow.doubanmovie.data.Actor
import java.lang.ref.WeakReference

/**
 * Created by aidchow on 17-5-30.
 */
class ActorItemViewModel : BaseObservable() {

    val actorObservable: ObservableField<Actor> = ObservableField()
    var mNavigator: WeakReference<ActorItemNavigator>? = null

    fun setNavigator(navigator: ActorItemNavigator?) {
        mNavigator = WeakReference<ActorItemNavigator>(navigator)
    }

    fun setActor(actor: Actor) {
        actorObservable.set(actor)
    }

    @Bindable
    fun getImageUrl(): String? {
        return actorObservable.get().imageUrl
    }

    @Bindable
    fun getActorName(): String? {
        return actorObservable.get().name
    }

    fun openCelebrity() {
        if (actorObservable.get().id == null) {
            return
        }
        if (mNavigator != null && mNavigator?.get() != null) {
            mNavigator?.get()!!.openCelebrity(actorObservable.get().id!!.toInt())
        }
    }
}