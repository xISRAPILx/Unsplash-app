package com.example.testupstarts.ui.photo_list_screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testupstarts.R
import com.example.testupstarts.databinding.RvItemBinding
import com.example.testupstarts.repository.models.PhotosItem
import com.example.testupstarts.ui.photo_list_screen.PhotosCallback
import com.squareup.picasso.Picasso

class PhotoAdapter(private val callback: PhotosCallback, private val flagResult: Boolean) :
    ListAdapter<PhotosItem, PhotoAdapter.PhotoViewHolder>(PhotoDiffUtil()) {

    private val photos: MutableList<PhotosItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(
        item = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }

    inner class PhotoViewHolder(private val item: RvItemBinding) : RecyclerView.ViewHolder(item.root) {
        init {
            item.root.setOnClickListener {
                callback.onItemClick(photos[layoutPosition])
            }
            item.itemFavBtn.setOnCheckedChangeListener { compoundButton, b ->
                photos[layoutPosition].favorite = b
                callback.onLikeClick(b, photos[layoutPosition])
            }
        }

        fun bind(photo: PhotosItem) {
            with(item) {
                Picasso.get()
                    .load(photo.imageUrlSmall)
                    .into(imgItem)
                author.text = photo.authorName
                itemLikes.text = itemView.context.getString(R.string.likes, photo.likes)
                itemFavBtn.isVisible = !flagResult
                itemFavBtn.isChecked = photo.favorite == true
            }
        }
    }

    fun setData(list: List<PhotosItem>) {
        photos.clear()
        photos.addAll(list)
    }
}