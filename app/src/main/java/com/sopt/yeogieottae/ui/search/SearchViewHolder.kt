package com.sopt.yeogieottae.ui.search

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.ItemHotelListBinding
import com.sopt.yeogieottae.network.response.Hotel

class SearchViewHolder(
    private val binding: ItemHotelListBinding,
    private val searchViewModel: SearchViewModel,
    private val itemClickEvent: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Hotel) {
        loadImage(item.imageUrl)
        setHotelInfo(item)
        updateLikeView(item.isLiked)
        initClickListener(item)
    }

    private fun loadImage(imageUrl: String) {
        Glide.with(binding.ivSearchlistHotelImg.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .override(120, 180)
            .centerCrop()
            .into(binding.ivSearchlistHotelImg)
    }

    private fun setHotelInfo(item: Hotel) {
        with(binding) {
            tvHotelName.text = item.hotelName
            tvHotelLocation.text = item.location
            tvHotelStarText.text = item.reviewRate.toString()
            tvHotelReviewCount.text = itemView.context.getString(R.string.hotel_review_count, item.reviewCount)
            tvHotelDiscountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            tvHotelTotalPrice.text = itemView.context.getString(R.string.all_price, item.price)
        }
    }

    private fun initClickListener(hotel: Hotel) {
        binding.loBtnfavorite.setOnClickListener {
            hotel.isLiked = !hotel.isLiked
            updateLikeView(hotel.isLiked)
            showSnackBar(it, hotel.isLiked)
            if (hotel.isLiked) {
                searchViewModel.postLikeHotel(hotel.hotelId)
            } else {
                searchViewModel.deleteLikeHotel(hotel.hotelId)
            }
        }

        binding.root.setOnClickListener {
            itemClickEvent(hotel.hotelId)
        }
    }

    private fun updateLikeView(isLiked: Boolean) {
        binding.ivBtnfavoriteOff.visibility = if (isLiked) View.GONE else View.VISIBLE
        binding.ivBtnfavoriteOn.visibility = if (isLiked) View.VISIBLE else View.GONE
    }

    private fun showSnackBar(view: View, isLiked: Boolean) {
        Snackbar.make(view, "Custom Snackbar", Snackbar.LENGTH_SHORT).apply {
            setCustomLayout(if (isLiked) R.layout.favorite_snackbar_on_layout else R.layout.favorite_snackbar_off_layout)
        }.show()
    }

    @SuppressLint("RestrictedApi")
    private fun Snackbar.setCustomLayout(layoutId: Int) {
        val customLayout = LayoutInflater.from(context).inflate(layoutId, null)
        val snackBarLayout = this.view as Snackbar.SnackbarLayout
        snackBarLayout.apply {
            setPadding(0, 0, 0, 330)
            removeAllViews()
            addView(customLayout)
            background = null
        }
    }
}