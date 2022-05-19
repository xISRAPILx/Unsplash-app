package com.example.testupstarts.ui.card_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.R
import com.example.testupstarts.App
import com.example.testupstarts.databinding.FragmentCardBinding
import com.example.testupstarts.databinding.WebviewLoginBinding
import com.example.testupstarts.repository.models.PhotosItem
import com.example.testupstarts.ui.auth_screen.AuthUnsplashViewModel
import com.example.testupstarts.ui.auth_screen.AuthViewModelFactory
import com.example.testupstarts.ui.main_screen.MainActivity
import com.example.testupstarts.ui.photo_list_screen.PhotoFragment
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_card.*
import javax.inject.Inject

class CardFragment : Fragment() {

    @Inject
    private lateinit var factory: CardViewModelFactory.Factory
    private val viewModel by viewModels<CardViewModel> {
        factory.create()
    }
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val PHOTO_ITEM = "photoItem"
        const val FLAG_GUEST = "guestFlag"

        fun newInstance(photo: PhotosItem, flag: Boolean): CardFragment {
            val bundle = Bundle().apply {
                putParcelable(PHOTO_ITEM, photo)
                putBoolean(FLAG_GUEST, flag)
            }
            return CardFragment().apply {
                arguments = bundle
            }
        }
    }

    private var photos: PhotosItem? = null
    private var flag: Boolean? = null

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
        flag = arguments?.getBoolean(FLAG_GUEST)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photos?.let {
            viewModel.onViewCreated(it.id, it.favorite)
            Picasso.get()
                .load(it.imageUrlRegular)
                .into(binding.imgCard)
            binding.cardLike.text =
                context?.getString(R.string.likes, it.likes)
            binding.cardAuthorUsername.text =
                context?.getString(R.string.author_username, it.authorUserName)
            binding.cardAuthorInsta.text =
                context?.getString(R.string.author_insta_username, it.instagramUsername)
            if (flag == true) {
                binding.cardFav.visibility = View.GONE
            } else {
                binding.cardFav.visibility = View.VISIBLE
            }
            viewModel.snackbar.observe(viewLifecycleOwner) {
                Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
            }
            viewModel.favorite.observe(viewLifecycleOwner) { binding.cardFav.isChecked = it }
            card_fav.setOnClickListener {
                viewModel.onFavClicked(binding.cardFav.isChecked, photos)
            }
        }
        binding.btnBack.setOnClickListener {
            (activity as? MainActivity)?.goBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}