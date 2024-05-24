package com.sopt.yeogieottae.ui.hotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.databinding.ItemHotelRoomBinding

class HotelRoomListAdapter(
    private val itemClickEvent: (Room) -> Unit,
    private val roomClickEvent: (Room) -> Unit,
) : ListAdapter<Room, HotelRoomViewHolder>(roomDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelRoomViewHolder {
        val binding = ItemHotelRoomBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HotelRoomViewHolder(binding, itemClickEvent, roomClickEvent)
    }

    override fun onBindViewHolder(holder: HotelRoomViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        val roomDiffer = object : DiffUtil.ItemCallback<Room>() {
            override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem.roomId == newItem.roomId
            }

            override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem == newItem
            }
        }
    }
}