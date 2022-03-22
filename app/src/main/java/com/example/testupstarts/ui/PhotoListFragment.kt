package com.example.testupstarts.ui

import android.app.Fragment
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
import com.example.testupstarts.databinding.FragmentCatalogBinding
import com.example.testupstarts.di.App
import com.example.testupstarts.repository.PhotosItem
import com.example.testupstarts.ui.list.PhotoAdapter
import com.example.testupstarts.viewmodels.PhotoListViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.Observer

class PhotoListFragment() : Fragment() {

    private lateinit var binding: FragmentCatalogBinding
    private lateinit var viewModel: PhotoListViewModel
    private lateinit var photosAdapter: PhotoAdapter
    private var flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            viewModelStore,
            App.instance.getAppContainer().getPhotoVmProvider()
        ).get(PhotoListViewModel::class.java)
        viewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //!
        (activity as? MainActivity)?.setSupportActionBar(binding.catalogToolbar)
        setViewModels(view)
        setAdapter()
    }

    private fun setViewModels(view: View) {
        viewModel.onViewCreated()
        viewModel.tokenFlag.observe(viewLifecycleOwner, Observer { tokenFlag ->
            flag = tokenFlag
        })
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

    private fun setAdapter() {
        binding.rvCatalog?.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCatalog?.setHasFixedSize(true)
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
        binding.rvCatalog?.adapter = photosAdapter
    }

    private fun openCard(photo: PhotosItem, flag: Boolean) {
        (activity as? MainActivity)?.startFragment(PhotoFragment.newInstance(photo, flag))
    }

    private fun showList(photos: List<PhotosItem>) {
        photosAdapter.submitList(photos)
        photosAdapter.setData(photos)
        binding.tvFound.text = context?.getString(R.string.found_text, photos.size)
        binding.tvFound.visibility = View.VISIBLE
        binding.rvCatalog.visibility = View.VISIBLE
        binding.progressBarCatalog.visibility = View.GONE
        binding.progressBarCatalog.hide()
        binding.errorMessage.visibility = View.GONE
    }

    private fun showError() {
        binding.tvFound.visibility = View.GONE
        binding.rvCatalog.visibility = View.GONE
        binding.progressBarCatalog.visibility = View.GONE
        binding.progressBarCatalog.hide()
        binding.errorMessage.visibility = View.VISIBLE
        binding.btnTryAgain.setOnClickListener { viewModel.onTryAgainClicked() }
    }

    fun showProgress() {
        binding.rvCatalog.visibility = View.GONE
        binding.progressBarCatalog.visibility = View.VISIBLE
        binding.progressBarCatalog.show()
        binding.errorMessage.visibility = View.GONE
        binding.tvFound.visibility = View.GONE
    }
}