package com.sopt.yeogieottae.ui.like

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.Like.FavoriteHotel
import com.sopt.yeogieottae.databinding.ItemFavoriteHotelBinding

class FavoriteHotelViewHolder(private val binding: ItemFavoriteHotelBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(hotelData: FavoriteHotel) {
        binding.run {
            tvFavoritehotelName.text = hotelData.hotelName
            tvFavoritehoteStarText.text = hotelData.reviewRate.toString()
            tvFavoritehotelRoomname.text = hotelData.roomName
            ivFavoritehotelRoom.setImageResource(hotelData.roomImage)
            tvHotelTotalPrice.text = hotelData.price.toString()
            tvHotelDiscountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }
}