package com.example.matrimony.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.matrimony.R
import android.view.View

import android.webkit.WebView
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.example.poultry_i.storageHelpers.PreferenceHelper


class CommonWebViewActivity : AppCompatActivity() {
    private lateinit var url: String
    private lateinit var toolbar_title: String
    private var isExpand: Boolean = false
    private lateinit var objWebView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_web_view)


        toolbar = findViewById(R.id.toolbar)
        objWebView = findViewById(R.id.webView_content)
        setSupportActionBar(toolbar)

        progressBar= findViewById(R.id.progressBar)

        val bundle: Bundle = intent.extras!!
        if (bundle != null) {
            toolbar_title = bundle.getString("toolbar_title").toString()
            url = bundle.getString("url").toString()
        }
        getSupportActionBar()!!.setTitle(toolbar_title);
        //txtTitle.text = toolbar_title
    }


    override fun onBackPressed() {
        isExpand = PreferenceHelper.setBooleanPreference(this, "isExpand", true)
        if (objWebView.canGoBack()) {
            objWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()

        objWebView.settings.javaScriptEnabled = true
        objWebView.settings.builtInZoomControls = true
        objWebView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        objWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        objWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                progressBar.visibility = View.GONE
                objWebView.visibility = View.VISIBLE

            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
            }
        }

        objWebView.loadUrl(url)
    }
}