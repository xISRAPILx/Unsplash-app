package com.example.testupstarts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testupstarts.PhotosCallback
import com.example.testupstarts.R
import com.example.testupstarts.di.App
import com.example.testupstarts.repository.PhotosItem
import com.example.testupstarts.ui.list.PhotoAdapter
import com.example.testupstarts.viewmodels.PhotoViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_catalog.*

class PhotoFragment() : Fragment() {

    private lateinit var viewModel: PhotoViewModel
    private lateinit var photosAdapter: PhotoAdapter
    private var flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            viewModelStore,
            App.instance.getAppContainer().getPhotoVmProvider()
        ).get(PhotoViewModel::class.java)
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //!
        (activity as? MainActivity)?.setSupportActionBar(catalog_toolbar)
        rv_catalog?.layoutManager = GridLayoutManager(requireContext(), 2)
        rv_catalog?.setHasFixedSize(true)
        viewModel.onViewCreated()
        viewModel.tokenFlag.observe(viewLifecycleOwner, Observer { tokenFlag ->
            flag = tokenFlag
        })
        photosAdapter = PhotoAdapter(
            object : PhotosCallback {
                override fun onItemClick(photo: PhotosItem) {
                    openCard(photo, flag)
                }
                override fun onLikeClick(like: Boolean, photo: PhotosItem) {
                    viewModel.onFavClicked(like, photo)
                }
            },
            flag
        )
        rv_catalog?.adapter = photosAdapter
        viewModel.snackbar.observe(viewLifecycleOwner, {
            Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        })
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                ProgressState -> showProgress()
                ErrorState -> showError()
                is ResultState -> showList(state.list)
            }
        })
    }

    private fun openCard(photo: PhotosItem, flag: Boolean) {
        (activity as? MainActivity)?.startFragment(CardFragment.newInstance(photo, flag))
    }

    private fun showList(photos: List<PhotosItem>) {
        photosAdapter.submitList(photos)
        photosAdapter.setData(photos)
        tv_found.text = context?.getString(R.string.found_text, photos.size)
        tv_found.visibility = View.VISIBLE
        rv_catalog.visibility = View.VISIBLE
        progress_bar_catalog.visibility = View.GONE
        progress_bar_catalog.hide()
        error_message.visibility = View.GONE
    }

    private fun showError() {
        tv_found.visibility = View.GONE
        rv_catalog.visibility = View.GONE
        progress_bar_catalog.visibility = View.GONE
        progress_bar_catalog.hide()
        error_message.visibility = View.VISIBLE
        btn_try_again.setOnClickListener { viewModel.onTryAgainClicked() }
    }

    fun showProgress() {
        rv_catalog.visibility = View.GONE
        progress_bar_catalog.visibility = View.VISIBLE
        progress_bar_catalog.show()
        error_message.visibility = View.GONE
        tv_found.visibility = View.GONE
    }
}