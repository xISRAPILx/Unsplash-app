package com.example.testupstarts.ui.main_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.R
import com.example.testupstarts.di.App
import com.example.testupstarts.ui.login_screen.LoginUnsplashFragment
import com.example.testupstarts.ui.photo_list_screen.PhotoFragment

class MainActivity : AppCompatActivity() {

    private val fragmentPhoto = PhotoFragment()
    private val fragmentLoginUnsplash = LoginUnsplashFragment()
    private val fragmentManager = supportFragmentManager
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            viewModelStore,
            App.instance.getAppContainer().getMainActivityVmProvider()
        ).get(MainActivityViewModel::class.java)

        viewModel.onCreate()

        viewModel.loginResult.observe(this, Observer { result ->
            if (result) {
                startRootFragment(fragmentLoginUnsplash)
            } else {
                startRootFragment(fragmentPhoto) }
        } )
    }

    fun startRootFragment(fragment: Fragment) {
        fragmentManager.popBackStack("auth", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        for (i in 0..fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.fragment_cont, fragment)
            .commit()
    }

    fun startFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.fragment_cont, fragment)
            .addToBackStack("tag")
            .commit()
    }

    fun goBack() {
        fragmentManager.popBackStack()
    }
}