package com.example.testupstarts.ui.photo_list_screen.list

import androidx.recyclerview.widget.DiffUtil
import com.example.testupstarts.repository.models.PhotoItem

class PhotoDiffUtil: DiffUtil.ItemCallback<PhotoItem>() {
    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean =
        oldItem == newItem
}