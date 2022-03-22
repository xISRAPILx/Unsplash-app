package com.example.testupstarts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testupstarts.databinding.FragmentLoginUnsplashBinding

class LoginUnsplashFragment: Fragment() {
    private lateinit var binding: FragmentLoginUnsplashBinding
    private val fragmentPhoto = PhotoListFragment()
    private val fragmentAuthUnsplash = AuthUnsplashFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            (activity as? MainActivity)?.startFragment(fragmentAuthUnsplash)
        }
        binding.textGuest.setOnClickListener {
            (activity as? MainActivity)?.startFragment(fragmentPhoto)
        }

    }
}