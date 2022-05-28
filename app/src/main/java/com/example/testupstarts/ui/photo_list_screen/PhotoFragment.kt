package com.example.testupstarts.ui.photo_list_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testupstarts.App
import com.example.testupstarts.R
import com.example.testupstarts.databinding.FragmentCatalogBinding
import com.example.testupstarts.repository.models.PhotoItem
import com.example.testupstarts.ui.ErrorState
import com.example.testupstarts.ui.ProgressState
import com.example.testupstarts.ui.ResultState
import com.example.testupstarts.ui.card_screen.CardFragment
import com.example.testupstarts.ui.main_screen.MainActivity
import com.example.testupstarts.ui.photo_list_screen.list.PhotoAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class PhotoFragment() : Fragment() {

    //todo Обновлять фотки по свайпу и пихать сначала все с кеша

    @Inject
    lateinit var factory: PhotoViewModelFactory.Factory
    private val viewModel by viewModels<PhotoViewModel> {
        factory.create()
    }
    private var photosAdapter: PhotoAdapter? = null
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setSupportActionBar(binding.catalogToolbar)
        with(binding) {
            rvCatalog.layoutManager = GridLayoutManager(requireContext(), 2)
            rvCatalog.setHasFixedSize(true)
            rvCatalog.adapter = photosAdapter
            swipe.isRefreshing = true
            swipe.setOnRefreshListener {
                viewModel.onPhotoListUpdated()
            }
        }
        viewModel.onViewCreated()
        viewModel.tokenFlag.observe(viewLifecycleOwner) { flag ->
            photosAdapter = PhotoAdapter(
                object : PhotosCallback {
                    override fun onItemClick(photo: PhotoItem) {
                        openCard(photo)
                    }

                    override fun onLikeClick(like: Boolean, photo: PhotoItem) {
                        viewModel.onFavClicked(like, photo)
                    }
                },
                flag
            )
        }

        viewModel.snackbar.observe(viewLifecycleOwner) {
            Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                ProgressState -> showProgress()
                ErrorState -> showError()
                is ResultState -> showList(state.list)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        photosAdapter = null
    }

    private fun injectDependencies() {
        App.appComponent.photoComponentBuilder().bindInflater(layoutInflater).build().inject(this)

    }

    private fun openCard(photo: PhotoItem) {
        (activity as? MainActivity)?.startFragment(CardFragment.newInstance(photo))
    }

    private fun showList(photos: List<PhotoItem>) {
        photosAdapter?.submitList(photos)
        photosAdapter?.setData(photos)
        with(binding) {
            tvFound.text = context?.getString(R.string.found_text, photos.size)
            tvFound.visibility = View.VISIBLE
            rvCatalog.visibility = View.VISIBLE
            progressBarCatalog.visibility = View.GONE
            progressBarCatalog.hide()
            errorMessage.visibility = View.GONE
        }
    }

    private fun showError() {
        with(binding) {
            tvFound.visibility = View.GONE
            rvCatalog.visibility = View.GONE
            progressBarCatalog.visibility = View.GONE
            progressBarCatalog.hide()
            errorMessage.visibility = View.VISIBLE
            btnTryAgain.setOnClickListener { viewModel.onPhotoListUpdated() }
        }
    }

    fun showProgress() {
        with(binding) {
            rvCatalog.visibility = View.GONE
            progressBarCatalog.visibility = View.VISIBLE
            progressBarCatalog.show()
            errorMessage.visibility = View.GONE
            tvFound.visibility = View.GONE
        }
    }
}