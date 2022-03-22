package com.example.testupstarts.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testupstarts.PhotosCallback
import com.example.testupstarts.R
import com.example.testupstarts.databinding.RvItemBinding
import com.example.testupstarts.repository.PhotosItem
import com.squareup.picasso.Picasso

class PhotoAdapter(private val callback: PhotosCallback, private val flagResult: Boolean) :
    ListAdapter<PhotosItem, PhotoAdapter.Photo1ViewHolder>(PhotoDiffUtil()) {
    private val photos: MutableList<PhotosItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Photo1ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return Photo1ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Photo1ViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }

    inner class Photo1ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private lateinit var binding: RvItemBinding

        init {
            item.setOnClickListener {
                callback.onItemClick(photos[layoutPosition])
            }
            binding.itemFavBtn.setOnClickListener {
                photos[layoutPosition].favorite = binding.itemFavBtn.isChecked
                callback.onLikeClick(binding.itemFavBtn.isChecked, photos[layoutPosition])
            }
        }

        fun bind(photo: PhotosItem) {
            Picasso.get()
                .load(photo.imageUrlSmall)
                .into(binding.imgItem)
            binding.author.text = photo.authorName
            binding.itemLikes.text = itemView.context.getString(R.string.likes, photo.likes)
            if (flagResult) {
                binding.itemFavBtn.visibility = View.GONE
            } else {
                binding.itemFavBtn.visibility = View.VISIBLE
            }
            binding.itemFavBtn.isChecked = photo.favorite
        }
    }

    fun setData(list: List<PhotosItem>) {
        photos.clear()
        photos.addAll(list)
    }
}