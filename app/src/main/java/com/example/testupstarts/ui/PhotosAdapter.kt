package com.example.testupstarts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testupstarts.PhotosCallback
import com.example.testupstarts.R
import com.example.testupstarts.repository.PhotosItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item.view.*

class JeansAdapter(private val callback: PhotosCallback) :
    RecyclerView.Adapter<JeansAdapter.JeansViewHolder>() {

    private val photos: MutableList<PhotosItem> = mutableListOf()

    inner class JeansViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

        private val photosImage = item.img_item
        private val author = item.author
        private val likes = item.item_likes
        private val favButton = item.item_fav_btn

        init {
            item.setOnClickListener {
                callback.onItemClick(photos[adapterPosition])
            }
            favButton.setOnClickListener {
                callback.onLikeClick(favButton.isChecked, photos[adapterPosition].id)
                photos[adapterPosition].favorite = favButton.isChecked
                notifyItemChanged(adapterPosition)
            }
        }

        fun bind(photos: PhotosItem) {
            Picasso.get()
                .load(photos.imageUrlSmall)
                .into(photosImage)
            author.text = photos.authorUserName
            likes.text = item.context.getString(R.string.likes, photos.likes)
            favButton.isChecked = photos.favorite
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JeansAdapter.JeansViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)

        return JeansViewHolder(view)
    }

    override fun onBindViewHolder(holder: JeansAdapter.JeansViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun setData(newJeans: List<PhotosItem>) {
        val diffCallback = PhotosDiffCallback(photos, newJeans)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        photos.clear()
        photos.addAll(newJeans)
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
