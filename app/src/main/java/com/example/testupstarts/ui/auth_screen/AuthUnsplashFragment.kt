package com.example.testupstarts.ui.auth_screen

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.R
import com.example.testupstarts.di.App
import com.example.testupstarts.ui.main_screen.MainActivity
import com.example.testupstarts.ui.photo_list_screen.PhotoFragment
import kotlinx.android.synthetic.main.webview_login.*

class AuthUnsplashFragment() : Fragment() {

    private lateinit var viewModel: AuthUnsplashViewModel
    private val fragmentPhoto = PhotoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            viewModelStore,
            App.instance.getAppContainer().getAuthUnsplashVmProvider()
        ).get(AuthUnsplashViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.webview_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tokenResult.observe(viewLifecycleOwner, Observer {
            (activity as? MainActivity)?.startRootFragment(fragmentPhoto)
        })

        webview_auth.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                val uri: Uri = Uri.parse(url)
                val code = uri.getQueryParameter("code")
                if (code != null) { viewModel.onAuthCodeExtracted(code) }
            }
        }
        webview_auth.loadUrl(AUTHORIZE_URL)
    }

    companion object {
        const val AUTHORIZE_URL: String =
            "https://unsplash.com/oauth/authorize?client_id=4-eO-OIi38lTUD6Oa3qEqSd8CBPNVHpBR3Bm-yzoYKk" +
                    "&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code&scope=public+write_likes+read_user+read_collections"
    }
}