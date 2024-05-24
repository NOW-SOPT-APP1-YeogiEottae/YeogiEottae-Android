package com.sopt.yeogieottae.ui.like

import android.graphics.Paint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.like.LikeHotelState
import com.sopt.yeogieottae.databinding.ItemFavoriteHotelBinding

class FavoriteHotelViewHolder(
    private val binding: ItemFavoriteHotelBinding,
    private val onItemLongClick: (LikeHotelState) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hotel: LikeHotelState) {
        with(binding) {
            if (hotel.isSuccess) {
                tvFavoritehotelName.text = hotel.hotelName
                tvFavoritehoteStarText.text = hotel.reviewRate.toString()
                tvFavoritehotelNameEmpty.text = hotel.hotelName
                tvFavoritehoteStarTextEmpty.text = hotel.reviewRate.toString()

                if (hotel.roomInformation == null) {
                    showEmptyRoom()
                } else {
                    showLikeRoom(hotel)
                }

                root.setOnLongClickListener {
                    onItemLongClick(hotel)
                    true
                }
            } else {
                Log.d("FavoriteHotelViewHolder", "Failed to load hotel data")
            }
        }
    }

    private fun showLikeRoom(hotel: LikeHotelState) {
        with(binding) {
            loFavoritehotelLikeNotempty.visibility = View.VISIBLE
            loFavoritehotelEmptyLikeroom.visibility = View.GONE
            tvFavoritehotelRoomname.text = hotel.roomName
            Glide.with(ivFavoritehotelRoom.context)
                .load(hotel.roomImage)
                .into(ivFavoritehotelRoom)
            tvHotelTotalPrice.text = itemView.context.getString(R.string.all_price, hotel.price)
            tvHotelDiscountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun showEmptyRoom() {
        with(binding) {
            loFavoritehotelLikeNotempty.visibility = View.GONE
            loFavoritehotelEmptyLikeroom.visibility = View.VISIBLE
        }
    }
}