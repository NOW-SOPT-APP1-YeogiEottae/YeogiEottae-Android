package com.sopt.yeogieottae.ui.hotel

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.databinding.ItemHotelRoomBinding

class HotelRoomViewHolder(
    private val binding: ItemHotelRoomBinding,
    private val ItemClickEvent: (Room) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(room: Room) {
        initView(room)
        loadImage(room.image_url)
        initButton(room)
    }

    private fun initView(room: Room) {
        with(binding) {
            tvRoomName.text = room.room_name
            tvRoomPrice.text = binding.root.context.getString(R.string.all_price_Int, room.price)
            updateLikeBtn(room.is_liked)
        }
    }

    private fun initButton(room: Room) {
        binding.ivRoomFavoriteBtn.setOnClickListener {
            ItemClickEvent(room)
            updateLikeBtn(room.is_liked)
        }
    }

    private fun updateLikeBtn(is_liked: Boolean) {
        binding.ivRoomFavorite.setImageResource(
            if (is_liked) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
        )
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