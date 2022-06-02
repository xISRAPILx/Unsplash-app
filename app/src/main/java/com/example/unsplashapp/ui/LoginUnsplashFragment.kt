package com.example.unsplashapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.unsplashapp.R
import kotlinx.android.synthetic.main.fragment_login_unsplash.*

class LoginUnsplashFragment: Fragment() {
    private val fragmentPhoto = PhotoFragment()
    private val fragmentAuthUnsplash = AuthUnsplashFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_unsplash, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_login.setOnClickListener {
            (activity as? MainActivity)?.startFragment(fragmentAuthUnsplash)
        }
        text_guest.setOnClickListener {
            (activity as? MainActivity)?.startFragment(fragmentPhoto)
        }

    }
}