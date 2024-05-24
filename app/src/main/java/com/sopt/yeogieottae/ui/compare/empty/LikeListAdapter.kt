package com.sopt.yeogieottae.ui.compare.empty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.yeogieottae.data.RoomInformation
import com.sopt.yeogieottae.databinding.ItemLikeListBinding

class LikeListAdapter(private val onCheckedChanged: (Boolean) -> Unit) :
    ListAdapter<RoomInformation, LikeListViewHolder>(DiffCallback()) {
    private val selectedItems = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeListViewHolder {
        val binding =
            ItemLikeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LikeListViewHolder(binding, selectedItems, onCheckedChanged)
    }

    override fun onBindViewHolder(holder: LikeListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    class DiffCallback : DiffUtil.ItemCallback<RoomInformation>() {
        override fun areItemsTheSame(oldItem: RoomInformation, newItem: RoomInformation): Boolean {
            return oldItem.roomId == newItem.roomId
        }

        override fun areContentsTheSame(
            oldItem: RoomInformation, newItem: RoomInformation,
        ): Boolean {
            return oldItem == newItem
        }
    }
}