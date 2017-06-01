package com.aidchow.doubanmovie.celebrity

import android.content.Intent
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.aidchow.doubanmovie.R
import com.aidchow.doubanmovie.adapter.CelebrityAdapter
import com.aidchow.doubanmovie.data.Celebrity
import com.aidchow.doubanmovie.databinding.FragmentCelebrityBinding
import com.aidchow.doubanmovie.movies.MainActivity
import com.aidchow.doubanmovie.ui.WebViewActivity
import com.aidchow.doubanmovie.util.SnackBarUtils
import kotlinx.android.synthetic.main.fragment_celebrity.*

/**
 * Created by aidchow on 17-5-31.
 */
class CelebrityFragment : Fragment(), CelebrityNavigator {


    private var mCelebrityViewModel: CelebrityViewModel? = null
    private var actorId: Int = 0
    private var mSnackBarCallBack: Observable.OnPropertyChangedCallback? = null

    companion object {
        fun newInstance(actorID: Int?): CelebrityFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt("ACTOR_ID", actorID!!)
            bundle.putBoolean("restore", false)
            val celebrityFragment = CelebrityFragment()
            celebrityFragment.arguments = bundle
            return celebrityFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        if (arguments.getBoolean("restore")) {
            return
        }
        actorId = arguments.getInt("ACTOR_ID", 0)
        if (actorId != 0) {
            mCelebrityViewModel?.start(actorId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.movie_subjiect_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_share -> if (mCelebrityViewModel?.getShareUrl() != null)
                startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND)
                        .setType("text/*").putExtra(Intent.EXTRA_TEXT, mCelebrityViewModel?.getShareUrl()),
                        activity.getString(R.string.share_to)))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val ab: ActionBar? = (activity as AppCompatActivity).supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

        val adapter: CelebrityAdapter = CelebrityAdapter(ArrayList<Celebrity.WorksBean>(0),
                R.layout.celebrity_item, activity as MainActivity)
        celebrity_recycler_view.layoutManager = LinearLayoutManager(activity)
        celebrity_recycler_view.adapter = adapter
        tv_reload.setOnClickListener { mCelebrityViewModel?.start(actorId) }
        mSnackBarCallBack = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                SnackBarUtils.showSnackBar(view, mCelebrityViewModel?.getSnackBarText())
            }
        }
        mCelebrityViewModel?.snackBarText?.addOnPropertyChangedCallback(mSnackBarCallBack)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentCelebrityBinding = FragmentCelebrityBinding.inflate(inflater, container, false)
        binding.viewModel = mCelebrityViewModel
        setHasOptionsMenu(true)
        val root = binding.root
        return root
    }

    override fun onPause() {
        super.onPause()
        arguments.putBoolean("restore", true)
    }

    override fun onDestroy() {
        if (mSnackBarCallBack != null) {
            mCelebrityViewModel?.snackBarText?.removeOnPropertyChangedCallback(mSnackBarCallBack)
        }
        mCelebrityViewModel?.onFragmentDestroyed()
        super.onDestroy()
    }

    fun setViewModel(viewModel: CelebrityViewModel) {
        mCelebrityViewModel = viewModel
        mCelebrityViewModel?.setNavigator(this)
    }

    override fun openActorMobileUrl(url: String?) {
        WebViewActivity.startActvity(activity, url)
    }
}