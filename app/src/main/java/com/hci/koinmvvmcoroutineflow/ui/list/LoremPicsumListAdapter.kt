package com.hci.koinmvvmcoroutineflow.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hci.koinmvvmcoroutineflow.databinding.ItemImageBinding
import com.hci.koinmvvmcoroutineflow.model.ImageInfo

class LoremPicsumListAdapter:
    PagingDataAdapter<ImageInfo, LoremPicsumListAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<ImageInfo>() {
            override fun areItemsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
                return oldItem.id == newItem.id && oldItem.author == newItem.author
            }

            override fun areContentsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
                return oldItem.id == newItem.id && oldItem == newItem
            }

        }
    ) {


    interface OnItemClickListener{
        fun onItemClick(item:ImageInfo)
    }

    var onItemClickListener:OnItemClickListener? = null

    override fun onBindViewHolder(holder: LoremPicsumListAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position)?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LoremPicsumListAdapter.ImageViewHolder {
        return ImageViewHolder(binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ImageInfo) {
            binding.item = item
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(item)
            }
        }
    }
}