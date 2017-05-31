package com.aidchow.doubanmovie.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.aidchow.doubanmovie.R
import kotlinx.android.synthetic.main.fragmeny_webview.*

/**
 * Created by aidchow on 17-5-31.
 */
class WebViewFragment : Fragment() {
    companion object {
        fun newInstance(url: String): WebViewFragment {
            val bundle: Bundle = Bundle()
            bundle.putString("URL", url)
            val webviewFrag = WebViewFragment()
            webviewFrag.arguments = bundle
            return webviewFrag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragmeny_webview, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val ab: ActionBar? = (activity as AppCompatActivity).supportActionBar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        ab?.setHomeAsUpIndicator(R.drawable.ic_close)
        ab?.setDisplayShowHomeEnabled(true)

        web_view.settings.javaScriptEnabled = true
        web_view.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    myProgressBar.visibility = View.INVISIBLE
                } else {
                    if (View.INVISIBLE == myProgressBar.visibility) {
                        myProgressBar.visibility = View.VISIBLE
                    }
                    myProgressBar.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }
        })

        web_view.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                web_view.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }
        })
        web_view.loadUrl(arguments?.getString("URL")!!)
    }

}