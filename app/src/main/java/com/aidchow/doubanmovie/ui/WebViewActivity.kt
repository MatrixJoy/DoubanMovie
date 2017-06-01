package com.aidchow.doubanmovie.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.*
import com.aidchow.doubanmovie.R
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by aidchow on 17-5-31.
 * use webview to load url
 */
class WebViewActivity : AppCompatActivity() {
    companion object {
        fun startActvity(context: Context, url: String?) {
            val intent: Intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("URL", url)
            context.startActivity(intent)
        }
    }

    private var lastedUrl: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        web_view.settings.javaScriptEnabled = true
        web_view.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
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

            override fun onReceivedTitle(view: WebView?, title: String?) {
                toolbar.title = title
            }

        })

        web_view.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                refresh.isRefreshing = false

            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                refresh.isRefreshing = false
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                web_view.loadUrl(request?.url.toString())
                lastedUrl = request?.url.toString()
                return true
            }
        })
        lastedUrl = intent?.getStringExtra("URL")!!
        load()
        refresh.setOnRefreshListener { load() }
    }

    private fun load() {
        web_view.loadUrl(lastedUrl)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (web_view.canGoBack()) {
            web_view.goBack()
        } else {
            super.onBackPressed()
        }
    }

}