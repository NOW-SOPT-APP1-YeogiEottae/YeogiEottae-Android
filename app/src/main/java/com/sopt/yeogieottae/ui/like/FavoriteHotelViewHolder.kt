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
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(likeHotelState: LikeHotelState) {
        if (likeHotelState.isSuccess) {
            initHotelViews(likeHotelState)
            if (likeHotelState.roomInformation == null) {
                showEmptyRoom()
            } else {
                showLikeRoom()
                initRoomView(likeHotelState)
            }
        } else {
            Log.d("likehotel", "실패..")
        }
    }

    private fun initHotelViews(likeHotelState: LikeHotelState) {
        with(binding) {
            tvFavoritehotelName.text = likeHotelState.hotelName
            tvFavoritehoteStarText.text = likeHotelState.reviewRate.toString()
            tvFavoritehotelNameEmpty.text = likeHotelState.hotelName
            tvFavoritehoteStarTextEmpty.text = likeHotelState.reviewRate.toString()
        }
    }

    private fun initRoomView(likeHotelState: LikeHotelState) {
        with(binding) {
            tvFavoritehotelRoomname.text = likeHotelState.roomName
            Glide.with(ivFavoritehotelRoom.context)
                .load(likeHotelState.roomImage)
                .into(ivFavoritehotelRoom)
            binding.tvHotelTotalPrice.text =
                itemView.context.getString(R.string.all_price).format(likeHotelState.price)
            tvHotelDiscountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun showLikeRoom() {
        with(binding) {
            loFavoritehotelLikeNotempty.visibility = View.VISIBLE
            loFavoritehotelEmptyLikeroom.visibility = View.GONE
        }
    }

    private fun showEmptyRoom() {
        with(binding) {
            loFavoritehotelLikeNotempty.visibility = View.GONE
            loFavoritehotelEmptyLikeroom.visibility = View.VISIBLE
        }
    }
}