package com.example.testupstarts.ui.photo_list_screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testupstarts.R
import com.example.testupstarts.databinding.RvItemBinding
import com.example.testupstarts.repository.models.PhotoItem
import com.example.testupstarts.ui.photo_list_screen.PhotosCallback
import com.squareup.picasso.Picasso

class
PhotoAdapter(private val callback: PhotosCallback) :
    ListAdapter<PhotoItem, PhotoAdapter.PhotoViewHolder>(PhotoDiffUtil()) {

    private var flagShowLike: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(
        item = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }

    inner class PhotoViewHolder(private val item: RvItemBinding) :
        RecyclerView.ViewHolder(item.root) {
        init {
            item.root.setOnClickListener {
                callback.onItemClick(this@PhotoAdapter.currentList[layoutPosition])
            }
            item.itemFavBtn.setOnCheckedChangeListener { compoundButton, b ->
                this@PhotoAdapter.currentList[layoutPosition].favorite = b
                callback.onLikeClick(b, this@PhotoAdapter.currentList[layoutPosition])
            }
        }

        fun bind(photo: PhotoItem) {
            with(item) {
                Picasso.get()
                    .load(photo.imageUrlSmall)
                    .into(imgItem)
                author.text = photo.authorName
                itemLikes.text = itemView.context.getString(R.string.likes, photo.likes)
                itemFavBtn.isVisible = flagShowLike
                itemFavBtn.isChecked = photo.favorite == true
            }
        }
    }

    fun submitList(items: List<PhotoItem>, flashShowLike: Boolean) {
//        val isLoggedInChanged = currentList.isNotEmpty() && flagResult != isLoggedIn

        flagShowLike = flashShowLike
        submitList(items)
    }
}
