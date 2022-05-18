package com.example.testupstarts.ui.photo_list_screen.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testupstarts.ui.photo_list_screen.PhotosCallback
import com.example.testupstarts.R
import com.example.testupstarts.repository.PhotosItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item.view.*

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
        private val photoImage = item.img_item
        private val author = item.author
        private val likes = item.item_likes
        private val favButton = item.item_fav_btn

        init {
            item.setOnClickListener {
                callback.onItemClick(photos[layoutPosition])
            }
            favButton.setOnClickListener {
                photos[layoutPosition].favorite = favButton.isChecked
                callback.onLikeClick(favButton.isChecked, photos[layoutPosition])
            }
        }

        fun bind(photo: PhotosItem) {
            Picasso.get()
                .load(photo.imageUrlSmall)
                .into(photoImage)
            author.text = photo.authorName
            likes.text = itemView.context.getString(R.string.likes, photo.likes)
            if (flagResult) {
                favButton.visibility = View.GONE
            } else {
                favButton.visibility = View.VISIBLE
            }
            favButton.isChecked = photo.favorite
        }
    }

    fun setData(list: List<PhotosItem>) {
        photos.clear()
        photos.addAll(list)
    }
}