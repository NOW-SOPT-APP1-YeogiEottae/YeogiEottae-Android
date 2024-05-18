package com.sopt.yeogieottae.ui.search

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.hotel.Hotel
import com.sopt.yeogieottae.databinding.ItemHotelListBinding

class SearchViewHolder(private val binding: ItemHotelListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(hotelData: Hotel) {
        binding.run {
            tvHotelName.text = hotelData.name
            tvHotelLocation.text = hotelData.location
            tvHotelRating.text = hotelData.rating
            tvHotelReviewCount.text = hotelData.reviewCount
            tvHotelDiscountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            initLikeView(hotelData.isLiked)
            initClickListner(hotelData)
        }
    }

    private fun initClickListner(hotel: Hotel) {
        binding.loBtnfavorite.setOnClickListener {
            // 찜 상태 토글
            hotel.isLiked = !hotel.isLiked

            // 찜 상태에 따라 이미지 변경
            if (hotel.isLiked) {
                binding.ivBtnfavoriteOff.visibility = View.GONE
                binding.ivBtnfavoriteOn.visibility = View.VISIBLE
                createSnackBar(it, "Custom Snackbar", Snackbar.LENGTH_SHORT).apply {
                    setHotelCustomLayout()
                }.show()
            } else {
                binding.ivBtnfavoriteOff.visibility = View.VISIBLE
                binding.ivBtnfavoriteOn.visibility = View.GONE
                createSnackBar(it, "Custom Snackbar", Snackbar.LENGTH_SHORT).apply {
                    setOffCustomLayout()
                }.show()
            }
        }
    }

    private fun initLikeView(isLiked: Boolean) {
        // 찜 상태에 따라 초기 이미지 설정
        if (isLiked) {
            binding.ivBtnfavoriteOff.visibility = View.GONE
            binding.ivBtnfavoriteOn.visibility = View.VISIBLE
        } else {
            binding.ivBtnfavoriteOff.visibility = View.VISIBLE
            binding.ivBtnfavoriteOn.visibility = View.GONE
        }
    }

    private fun createSnackBar(view: View, message: String, duration: Int): Snackbar {
        return Snackbar.make(view, message, duration)
    }

    // 호텔찜 on snackbar
    @SuppressLint("RestrictedApi")
    private fun Snackbar.setHotelCustomLayout() {
        val customLayout =
            LayoutInflater.from(context).inflate(R.layout.favorite_hotel_snackbar_layout, null)

        val snackBarLayout = this.view as Snackbar.SnackbarLayout
        snackBarLayout.apply {
            setPadding(0, 0, 0, 330)
            removeAllViews()
            addView(customLayout)

            // 배경색을 투명하게 설정
            background = null
        }
    }

    // off snackbar
    @SuppressLint("RestrictedApi")
    private fun Snackbar.setOffCustomLayout() {
        val customLayout =
            LayoutInflater.from(context).inflate(R.layout.favorite_snackbar_off_layout, null)

        val snackBarLayout = this.view as Snackbar.SnackbarLayout
        snackBarLayout.apply {
            setPadding(0, 0, 0, 300)
            removeAllViews()
            addView(customLayout)

            // 배경색을 투명하게 설정
            background = null
        }
    }
}