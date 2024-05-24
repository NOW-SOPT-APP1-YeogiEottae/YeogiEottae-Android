package com.sopt.yeogieottae.ui.compare

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.Room
import com.sopt.yeogieottae.databinding.ItemCompareRoomBinding

class RoomAdapter :
    ListAdapter<Room, RoomAdapter.HotelViewHolder>(RoomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val binding = ItemCompareRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HotelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HotelViewHolder(private val binding: ItemCompareRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val detailAdapter = RoomDetailAdapter(listOf())

        init {
            binding.rvCompareDetail.adapter = detailAdapter
        }

        fun bind(hotel: Room) {
            binding.ivRoom.setImageResource(R.drawable.ic_launcher_background)
            detailAdapter.updateDetails(listOf(hotel))
        }
    }

    class RoomDiffCallback : DiffUtil.ItemCallback<Room>() {
        override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.roomId == newItem.roomId
        }

        override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem == newItem
        }
    }
}