package com.example.testupstarts.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.databinding.WebviewLoginBinding
import com.example.testupstarts.di.App
import com.example.testupstarts.viewmodels.AuthUnsplashViewModel

class AuthUnsplashFragment() : Fragment() {

    private lateinit var binding : WebviewLoginBinding
    private lateinit var viewModel: AuthUnsplashViewModel
    private val fragmentPhoto = PhotoListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        binding.webviewAuth.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                val uri: Uri = Uri.parse(url)
                val code = uri.getQueryParameter("code")
                if (code != null) { viewModel.onAuthCodeExtracted(code) }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
        binding.webviewAuth.loadUrl(AUTHORIZE_URL)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            viewModelStore,
            App.instance.getAppContainer().getAuthUnsplashVmProvider()
        ).get(AuthUnsplashViewModel::class.java)
    }

    private fun setObserver() {
        viewModel.tokenResult.observe(viewLifecycleOwner, Observer {
            (activity as? MainActivity)?.startRootFragment(fragmentPhoto)
        })
    }

    companion object {
        const val AUTHORIZE_URL: String =
            "https://unsplash.com/oauth/authorize?client_id=4-eO-OIi38lTUD6Oa3qEqSd8CBPNVHpBR3Bm-yzoYKk" +
                    "&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code&scope=public+write_likes+read_user+read_collections"
    }
}