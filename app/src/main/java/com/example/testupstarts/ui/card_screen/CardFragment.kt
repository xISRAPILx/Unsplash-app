package com.example.testupstarts.ui.card_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testupstarts.R
import com.example.testupstarts.databinding.FragmentCardBinding
import com.example.testupstarts.repository.models.PhotoItem
import com.example.testupstarts.ui.main_screen.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CardFragment () : Fragment() {

    @Inject
    lateinit var factory: CardViewModelFactory.Factory
    private val viewModel by viewModels<CardViewModel> {
        factory.create()
    }
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val PHOTO_ITEM = "photoItem"

        fun newInstance(photo: PhotoItem): CardFragment {
            val bundle = Bundle().apply {
                putParcelable(PHOTO_ITEM, photo)
            }
            return CardFragment().apply {
                arguments = bundle
            }
        }
    }

    private var photos: PhotoItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photos = arguments?.getParcelable(PHOTO_ITEM)
        viewModel.onCreate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photos?.let {
            it.favorite?.let { it1 -> viewModel.onViewCreated(it.id, it1) }
            Picasso.get()
                .load(it.imageUrlRegular)
                .into(binding.imgCard)
            binding.cardLike.text =
                context?.getString(R.string.likes, it.likes)
            binding.cardAuthorUsername.text =
                context?.getString(R.string.author_username, it.authorUserName)
            binding.cardAuthorInsta.text =
                context?.getString(R.string.author_insta_username, it.instagramUsername)
            viewModel.loggedUserValue.observe(viewLifecycleOwner) { isLogged ->
                binding.cardFav.isVisible = isLogged
            }
            viewModel.snackbar.observe(viewLifecycleOwner) { n ->
                Snackbar.make(view, n, Snackbar.LENGTH_SHORT).show()
            }
            viewModel.favorite.observe(viewLifecycleOwner) { isFavorite ->
                binding.cardFav.isChecked = isFavorite
            }
            binding.cardFav.setOnCheckedChangeListener { compoundButton, b ->
                viewModel.onFavClicked(b,photos)
            }
        }
        binding.btnBack.setOnClickListener {
            (requireActivity() as? MainActivity)?.goBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}