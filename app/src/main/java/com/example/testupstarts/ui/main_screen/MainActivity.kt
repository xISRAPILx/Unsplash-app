package com.example.testupstarts.ui.main_screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.testupstarts.App
import com.example.testupstarts.R
import com.example.testupstarts.databinding.ActivityMainBinding
import com.example.testupstarts.ui.login_screen.LoginUnsplashFragment
import com.example.testupstarts.ui.photo_list_screen.PhotoFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val fragmentPhoto = PhotoFragment()
    private val fragmentLoginUnsplash = LoginUnsplashFragment()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: MainActivityViewModelFactory.Factory
    private val viewModel by viewModels<MainActivityViewModel> {
        factory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectDependencies()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.onCreate()
        viewModel.loginResult.observe(this) { hasLogin ->
            if (!hasLogin) {
                startRootFragment(fragmentLoginUnsplash)
            } else {
                startRootFragment(fragmentPhoto)
            }
        }
    }

    private fun injectDependencies() {
        App.appComponent.mainComponentBuilder().bindInflater(layoutInflater).build().inject(this)
    }

    fun startRootFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack(AUTH_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction
                .replace(R.id.fragment_cont, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

    fun startFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction
                .replace(R.id.fragment_cont, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

    fun goBack() {
        supportFragmentManager.popBackStack()
    }
    //todo sdelat navigation component

    companion object {
        const val AUTH_TAG = "auth"
    }
}