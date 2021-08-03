package com.example.testupstarts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testupstarts.R
import com.example.testupstarts.di.App
import com.example.testupstarts.repository.PhotosItem
import com.example.testupstarts.viewmodels.CardViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_card.*

class CardFragment : Fragment() {

    private lateinit var viewModel : CardViewModel

    companion object {
        const val JEANS_ITEM = "jeansItem"

        fun newInstance(jeans: PhotosItem): CardFragment {
            val bundle = Bundle().apply {
                putParcelable(JEANS_ITEM, jeans)
            }
            return CardFragment().apply {
                arguments = bundle
            }
        }
    }
    private var jeans: PhotosItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            viewModelStore,
            App.instance.getAppContainer().getCardVmProvider()
        ).get(CardViewModel::class.java)
        jeans = arguments?.getParcelable(JEANS_ITEM)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jeans?.let {
            Picasso.get()
                .load(it.imageUrlRegular)
                .into(img_card)
            card_like.text =
                context?.getString(R.string.likes, it.likes)
            card_author_username.text = context?.getString(R.string.author_username, it.authorUserName)
            card_author_insta.text = context?.getString(R.string.author_insta_username, it.instagramUsername)
            viewModel.onViewCreated(it.id, it.favorite)
            viewModel.snackbar.observe(viewLifecycleOwner, {
                Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
            })
            viewModel.favorite.observe(viewLifecycleOwner, { card_fav.isChecked = it })
            card_fav.setOnClickListener { view ->
                viewModel.onFavClicked(card_fav.isChecked)
            }
        }
        btn_back.setOnClickListener {
            (activity as? MainActivity)?.goBack()
        }
    }
}