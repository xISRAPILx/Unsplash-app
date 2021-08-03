package com.example.testupstarts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testupstarts.R

class MainActivity : AppCompatActivity() {

    private val fragmentCatalog = CatalogFragment()
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            startRootFragment(fragmentCatalog)
        }
    }

    fun startRootFragment(fragment: Fragment) {
        for (i in 1..fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .add(R.id.fragment_cont, fragment)
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