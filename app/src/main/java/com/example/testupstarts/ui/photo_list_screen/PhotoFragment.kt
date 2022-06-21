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
import com.example.testupstarts.ui.card_screen.CardFragment
import com.example.testupstarts.ui.main_screen.MainActivity
import com.example.testupstarts.ui.photo_list_screen.list.PhotoAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class PhotoFragment : Fragment() {

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
        //!
        (requireActivity() as? MainActivity)?.setSupportActionBar(binding.catalogToolbar)
        binding.rvCatalog.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCatalog.setHasFixedSize(true)
        viewModel.onViewCreated()
        photosAdapter = PhotoAdapter(
            object : PhotosCallback {
                override fun onItemClick(photo: PhotoItem) {
                    openCard(photo)
                }

                override fun onLikeClick(like: Boolean, photo: PhotoItem) {
                    viewModel.onFavClicked(like, photo)
                }
            }
        )
        binding.rvCatalog.adapter = photosAdapter
        viewModel.snackbar.observe(viewLifecycleOwner) {
            Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.photosLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                ProgressState -> showProgress()
                ErrorState -> showError()
                is ResultState -> {
                    showList(photos = state.photos, isLoggedIn = state.isLogged)
                }
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
        (requireActivity() as? MainActivity)?.startFragment(CardFragment.newInstance(photo))
    }

    private fun showList(photos: List<PhotoItem>, isLoggedIn: Boolean) {
        photosAdapter?.submitList(photos, isLoggedIn)

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
        binding.btnTryAgain.setOnClickListener { viewModel.onPhotoListUpdated() }
    }

    fun showProgress() {
        binding.rvCatalog.visibility = View.GONE
        binding.progressBarCatalog.visibility = View.VISIBLE
        binding.progressBarCatalog.show()
        binding.errorMessage.visibility = View.GONE
        binding.tvFound.visibility = View.GONE
    }
}
