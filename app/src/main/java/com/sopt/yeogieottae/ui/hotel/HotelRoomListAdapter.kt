package com.sopt.yeogieottae.ui.hotel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.databinding.ItemHotelRoomBinding

class HotelRoomListAdapter(context: Context) :
    ListAdapter<Room, RecyclerView.ViewHolder>(roomDiffer) {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        HotelRoomViewHolder(ItemHotelRoomBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HotelRoomViewHolder -> holder.onBind(currentList[position])
        }
    }

    companion object {

        val roomDiffer by lazy {
            object : DiffUtil.ItemCallback<Room>() {

                override fun areItemsTheSame(
                    oldItem: Room,
                    newItem: Room
                ): Boolean {
                    return (oldItem.room_id == newItem.room_id)
                }

                override fun areContentsTheSame(
                    oldItem: Room,
                    newItem: Room
                ): Boolean {
                    return oldItem == newItem
                }

            }
        }
    }
}

