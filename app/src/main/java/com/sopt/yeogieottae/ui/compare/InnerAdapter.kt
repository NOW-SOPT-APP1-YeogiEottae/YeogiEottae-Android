package com.sopt.yeogieottae.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.yeogieottae.databinding.ItemCompareDetailBinding
import com.sopt.yeogieottae.network.response.ResponseCompareRoom

class InnerAdapter(
    private val onItemClicked: (ResponseCompareRoom) -> Unit
) : ListAdapter<ResponseCompareRoom, InnerViewHolder>(InnerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val binding = ItemCompareDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InnerViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class InnerDiffCallback : DiffUtil.ItemCallback<ResponseCompareRoom>() {
        override fun areItemsTheSame(oldItem: ResponseCompareRoom, newItem: ResponseCompareRoom): Boolean {
            return oldItem.roomId == newItem.roomId
        }

        override fun areContentsTheSame(oldItem: ResponseCompareRoom, newItem: ResponseCompareRoom): Boolean {
            return oldItem == newItem
        }
    }
}