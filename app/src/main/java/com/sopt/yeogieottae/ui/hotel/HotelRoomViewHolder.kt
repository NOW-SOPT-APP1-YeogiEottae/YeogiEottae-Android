package com.sopt.yeogieottae.ui.hotel

import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.databinding.ItemHotelRoomBinding
import com.bumptech.glide.Glide

class HotelRoomViewHolder(private val binding: ItemHotelRoomBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(room: Room) {
        binding.run {
            tvRoomName.text = room.room_name
            tvRoomPrice.text = binding.root.context.getString(R.string.all_price_Int, room.price)
        }
        loadImage(room.image_url)
    }

    private fun loadImage(imageUrl: String) {
        val imageSize = binding.ivRoom.width
        Glide.with(binding.ivRoom.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .override(imageSize, imageSize)
            .into(binding.ivRoom)
    }
}