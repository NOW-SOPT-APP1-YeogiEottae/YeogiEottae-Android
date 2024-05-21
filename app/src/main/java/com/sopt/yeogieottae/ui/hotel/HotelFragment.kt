package com.sopt.yeogieottae.ui.hotel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentHotelBinding
import com.sopt.yeogieottae.util.BaseFragment
import com.sopt.yeogieottae.util.bulletSpanText


class HotelFragment : BaseFragment<FragmentHotelBinding>(
    FragmentHotelBinding::inflate
) {
    private val viewModel: HotelViewModel by viewModels()
    private lateinit var hotelRoomListAdapter: HotelRoomListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeViewModel()
        viewModel.getHotelInfo(1)
        initBtnClickListener()
    }

    private fun observeViewModel() {
        viewModel.hotel.observe(viewLifecycleOwner) { hotel ->
            hotel?.let {
                updateHotelView(hotel)
                hotelRoomListAdapter.submitList(it.room_list)
            }
        }
        viewModel.hotel_info.observe(viewLifecycleOwner) { hotelInfo ->
            hotelInfo?.let {
                updateHotelInfoView(hotelInfo)
            }
        }
    }

    private fun initBtnClickListener(){
        with(binding){
            ivRoomFavoriteBtn.setOnClickListener {
                viewModel.setLike()
            }
        }
    }

    private fun updateHotelView(hotel: HotelViewModel.Hotel) {
        with(binding) {
            tvHotelStar.text = hotel.star
            tvHotelName.text = hotel.name
            tvStarRate.text = hotel.review_rate.toString()
            tvTotalReview.text = hotel.review_count.toString()
            tvMap.text = hotel.location
            // Update favorite button icon
            val iconResId = if (hotel.is_liked) {
                R.drawable.ic_favorite_on
            } else {
                R.drawable.ic_favorite_off
            }
            ivRoomFavorite.setImageResource(iconResId)
        }
    }

    private fun updateHotelInfoView(hotelInfo: HotelViewModel.Hotel_info) {
        with(binding) {
            tvHotelEventContents.text = bulletSpanText(requireContext(), hotelInfo.event)
            tvPayTossContents.text = bulletSpanText(requireContext(), hotelInfo.pay)
        }
    }

    private fun initAdapter() {
        hotelRoomListAdapter = HotelRoomListAdapter(requireContext())
        binding.rvRoom.adapter = hotelRoomListAdapter
        binding.rvRoom.layoutManager = LinearLayoutManager(requireContext())
    }


}