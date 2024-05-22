package com.sopt.yeogieottae.ui.hotel

import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.databinding.ItemHotelRoomBinding

class HotelRoomViewHolder(private val binding: ItemHotelRoomBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(room: Room) {
        binding.run {
            tvRoomName.text = room.room_name
            tvRoomPrice.text = room.price.toString()//todo 추후 room 과 합쳐질시 string 값사용 tv_in_out 도
        }
    }
}