package com.example.unsplashapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.unsplashapp.PhotosCallback
import com.example.unsplashapp.R
import com.example.unsplashapp.repository.PhotosItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item.view.*

class PhotosAdapter(private val callback: PhotosCallback, private val flagResult: Boolean) :
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {
    private val photos: MutableList<PhotosItem> = mutableListOf()

    inner class PhotoViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

        private val photoImage = item.img_item
        private val author = item.author
        private val likes = item.item_likes
        private val favButton = item.item_fav_btn

        init {
            item.setOnClickListener {
                callback.onItemClick(photos[adapterPosition])
            }
            favButton.setOnClickListener {
                photos[adapterPosition].favorite = favButton.isChecked
                callback.onLikeClick(favButton.isChecked, photos[adapterPosition], photos)
            }
        }

        fun bind(photo: PhotosItem) {
            Picasso.get()
                .load(photo.imageUrlSmall)
                .into(photoImage)
            author.text = photo.authorName
            likes.text = item.context.getString(R.string.likes, photo.likes)
            if (flagResult) {
                favButton.visibility = View.GONE
            } else {
                favButton.visibility = View.VISIBLE
            }
            favButton.isChecked = photo.favorite
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotosAdapter.PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)

        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosAdapter.PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun setData(newPhotos: List<PhotosItem>) {
        val diffCallback = PhotosDiffCallback(photos, newPhotos)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        photos.clear()
        photos.addAll(newPhotos)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateData(newPhotos: List<PhotosItem>) {
        val diffCallback = PhotosDiffCallback(photos, newPhotos)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }
}

class PhotosDiffCallback(
    private val oldList: List<PhotosItem>,
    private val newList: List<PhotosItem>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}
