package com.sopt.yeogieottae.ui.search

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.hotel.Hotel
import com.sopt.yeogieottae.databinding.ItemHotelListBinding

class SearchViewHolder(private val binding: ItemHotelListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Hotel) {
        loadImage(item.imageUrl)
        setHotelInfo(item)
        initLikeView(item.isLiked)
        initClickListner(item)
    }

    private fun loadImage(imageUrl: String) {
        val imageSize = binding.ivSearchlistHotelImg.width
        Glide.with(binding.ivSearchlistHotelImg.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .override(imageSize, imageSize)
            .centerCrop()
            .into(binding.ivSearchlistHotelImg)
    }

    private fun setHotelInfo(item: Hotel) {
        with(binding) {
            tvHotelName.text = item.hotelName
            tvHotelLocation.text = item.location
            tvHotelRating.text = item.reviewRate.toString()
            tvHotelReviewCount.text = item.reviewCount.toString()
            tvHotelDiscountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            tvHotelTotalPrice.text =
                itemView.context.getString(R.string.all_price).format(item.price)
        }
    }

    private fun initClickListner(hotelData: Hotel) {
        binding.loBtnfavorite.setOnClickListener {
            // 찜 상태 토글
            hotelData.isLiked = !hotelData.isLiked

            // 찜 상태에 따라 이미지 변경
            if (hotelData.isLiked) {
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