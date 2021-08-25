package com.example.testupstarts.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.example.testupstarts.repository.PhotosItem

class PhotoDiffUtil: DiffUtil.ItemCallback<PhotosItem>() {
    override fun areItemsTheSame(oldItem: PhotosItem, newItem: PhotosItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PhotosItem, newItem: PhotosItem): Boolean =
        oldItem == newItem
}