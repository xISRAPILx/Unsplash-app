package com.example.testupstarts.ui.auth_screen

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.R
import com.example.testupstarts.App
import com.example.testupstarts.databinding.WebviewLoginBinding
import com.example.testupstarts.ui.main_screen.MainActivity
import com.example.testupstarts.ui.photo_list_screen.PhotoFragment
import kotlinx.android.synthetic.main.webview_login.*
import javax.inject.Inject

class AuthUnsplashFragment : Fragment() {

    @Inject
    lateinit var factory: AuthViewModelFactory.Factory
    private val viewModel by viewModels<AuthUnsplashViewModel> {
        factory.create()
    }
    private val fragmentPhoto = PhotoFragment()
    private var _binding: WebviewLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    private fun injectDependencies() {
        App.appComponent.authComponentBuilder().bindInflater(layoutInflater).build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WebviewLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tokenResult.observe(viewLifecycleOwner) {
            (activity as? MainActivity)?.startRootFragment(fragmentPhoto)
        }

        webview_auth.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                val uri: Uri = Uri.parse(url)
                val code = uri.getQueryParameter(QUERY)
                if (code != null) { viewModel.onAuthCodeExtracted(code) }
            }
        }
        webview_auth.loadUrl(AUTHORIZE_URL)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val QUERY = "code"
        const val AUTHORIZE_URL: String =
            "https://unsplash.com/oauth/authorize?client_id=4-eO-OIi38lTUD6Oa3qEqSd8CBPNVHpBR3Bm-yzoYKk" +
                    "&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code&scope=public+write_likes+read_user+read_collections"
    }
}