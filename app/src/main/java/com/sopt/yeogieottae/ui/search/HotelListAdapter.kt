package com.sopt.yeogieottae.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.hotel.Hotel

class HotelListAdapter : RecyclerView.Adapter<HotelListAdapter.HotelViewHolder>() {

    private var hotels = emptyList<Hotel>()

    inner class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tv_hotel_name)
        val locationTextView: TextView = itemView.findViewById(R.id.tv_hotel_location)
        val ratingTextView: TextView = itemView.findViewById(R.id.tv_hotel_rating)
        val reviewCountTextView: TextView = itemView.findViewById(R.id.tv_hotel_review_count)
        val btHotelLikeView: FrameLayout = itemView.findViewById(R.id.lo_btnfavorite)
        val btHotelLikeOn: ImageView = itemView.findViewById(R.id.iv_btnfavorite_on)
        val btHotelLikeOff: ImageView = itemView.findViewById(R.id.iv_btnfavorite_off)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel_list, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.nameTextView.text = hotel.name
        holder.locationTextView.text = hotel.location
        holder.ratingTextView.text = hotel.rating
        holder.reviewCountTextView.text = hotel.reviewCount

        // 찜 상태에 따라 초기 이미지 설정
        if (hotel.isLiked) {
            holder.btHotelLikeOn.visibility = View.VISIBLE
            holder.btHotelLikeOff.visibility = View.GONE
        } else {
            holder.btHotelLikeOn.visibility = View.GONE
            holder.btHotelLikeOff.visibility = View.VISIBLE
        }

        // 찜 버튼 클릭 이벤트 처리
        holder.btHotelLikeView.setOnClickListener {
            // 찜 상태 토글
            hotel.isLiked = !hotel.isLiked

            // 찜 상태에 따라 이미지 변경
            if (hotel.isLiked) {
                holder.btHotelLikeOn.visibility = View.VISIBLE
                holder.btHotelLikeOff.visibility = View.GONE
                createSnackBar(it, "Custom Snackbar", Snackbar.LENGTH_SHORT).apply {
                    setHotelCustomLayout()
                }.show()
            } else {
                holder.btHotelLikeOn.visibility = View.GONE
                holder.btHotelLikeOff.visibility = View.VISIBLE
                createSnackBar(it, "Custom Snackbar", Snackbar.LENGTH_SHORT).apply {
                    setOffCustomLayout()
                }.show()
            }
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
            setPadding(0, 0, 0, 0)
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
            setPadding(0, 0, 0, 0)
            removeAllViews()
            addView(customLayout)

            // 배경색을 투명하게 설정
            background = null
        }
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    fun setHotels(hotels: List<Hotel>) {
        this.hotels = hotels
        notifyDataSetChanged()
    }
}
