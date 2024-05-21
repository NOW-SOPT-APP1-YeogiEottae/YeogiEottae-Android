package com.sopt.yeogieottae.ui.compare.empty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.yeogieottae.databinding.ItemCompareLikeListBinding
import com.sopt.yeogieottae.network.response.CompareLikesRoom

class LikeListAdapter(private val onCheckedChanged: (Int, Boolean) -> Unit) :
    ListAdapter<CompareLikesRoom, LikeListViewHolder>(DiffCallback()) {
    private val selectedItems = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeListViewHolder {
        val binding =
            ItemCompareLikeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LikeListViewHolder(binding, selectedItems, onCheckedChanged)
    }

    override fun onBindViewHolder(holder: LikeListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<CompareLikesRoom>() {
        override fun areItemsTheSame(oldItem: CompareLikesRoom, newItem: CompareLikesRoom): Boolean {
            return oldItem.roomId == newItem.roomId
        }

        override fun areContentsTheSame(
            oldItem: CompareLikesRoom, newItem: CompareLikesRoom,
        ): Boolean {
            return oldItem == newItem
        }
    }
}