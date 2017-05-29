package com.aidchow.doubanmovie

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Created by aidchow on 17-5-27.
 * Non-UI Fragment used to retain ViewModel
 */
class ViewModelHolder<VM> : Fragment() {
    var mViewModel: VM? = null
        private set

    companion object {
        fun <M> createContainer(viewMoel: M): ViewModelHolder<M> {
            val viewModelContainer = ViewModelHolder<M>()
            viewModelContainer.setViewModel(viewMoel)
            return viewModelContainer
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun setViewModel(viewModel: VM) {
        mViewModel = viewModel
    }
}
