package com.example.testupstarts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testupstarts.PhotosCallback
import com.example.testupstarts.R
import com.example.testupstarts.di.App
import com.example.testupstarts.repository.PhotosItem
import com.example.testupstarts.viewmodels.CatalogViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_catalog.*

class CatalogFragment : Fragment() {

    private lateinit var viewModel: CatalogViewModel
    private lateinit var jeansAdapter: JeansAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            viewModelStore,
            App.instance.getAppContainer().getCatalogVmProvider()
        ).get(CatalogViewModel::class.java)
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
        (activity as? MainActivity)?.setSupportActionBar(catalog_toolbar)
        rv_catalog?.layoutManager = GridLayoutManager(requireContext(), 2)
        rv_catalog?.setHasFixedSize(true)
        jeansAdapter = JeansAdapter(object : PhotosCallback {
            override fun onItemClick(jeans: PhotosItem) {
                openCard(jeans)
            }
            override fun onLikeClick(like: Boolean, id: String) {
                viewModel.onFavClicked(like, id)
            }
        })

        viewModel.snackbar.observe(viewLifecycleOwner, {
            Snackbar.make(view,it, Snackbar.LENGTH_SHORT).show()
        })

        rv_catalog?.adapter = jeansAdapter
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                ProgressState -> showProgress()
                ErrorState -> showError()
                is ListState -> showList(state.list)
            }
        })
        viewModel.onViewCreated()
    }

    private fun openCard(jeans: PhotosItem) {
        (activity as? MainActivity)?.startFragment(CardFragment.newInstance(jeans))
    }

    private fun showList(jeans: List<PhotosItem>) {
        tv_found.text = context?.getString(R.string.found_text, jeans.size)
        tv_found.visibility = View.VISIBLE
        jeansAdapter.setData(jeans)
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
        btn_try_again.setOnClickListener {
            viewModel.onTryAgainClicked()
        }
    }

    fun showProgress() {
        rv_catalog.visibility = View.GONE
        progress_bar_catalog.visibility = View.VISIBLE
        progress_bar_catalog.show()
        error_message.visibility = View.GONE
        tv_found.visibility = View.GONE
    }
}