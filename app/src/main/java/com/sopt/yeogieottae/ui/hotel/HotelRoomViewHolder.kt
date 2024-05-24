package com.sopt.yeogieottae.ui.hotel

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.databinding.ItemHotelRoomBinding

class HotelRoomViewHolder(
    private val binding: ItemHotelRoomBinding,
    private val itemClickEvent: (Room) -> Unit,
    private val roomClickEvent: (Room) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(room: Room) {
        with(binding) {
            tvRoomName.text = room.roomName
            tvRoomPrice.text = root.context.getString(R.string.all_price_Int, room.price)
            updateLikeButton(room.isLiked)
            ivRoomFavoriteBtn.setOnClickListener { itemClickEvent(room) }
            root.setOnClickListener { roomClickEvent(room) }
            Glide.with(ivRoom.context)
                .load(room.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivRoom)
        }

    }

    private fun updateLikeButton(isLiked: Boolean) {
        binding.ivRoomFavorite.setImageResource(
            if (isLiked) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
        )
    }
}