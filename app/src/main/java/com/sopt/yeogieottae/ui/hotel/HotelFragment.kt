package com.sopt.yeogieottae.ui.hotel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentHotelBinding
import com.sopt.yeogieottae.util.BaseFragment
import com.sopt.yeogieottae.util.bulletSpanText

class HotelFragment : BaseFragment<FragmentHotelBinding>(
    FragmentHotelBinding::inflate
) {
    private val viewModel: HotelViewModel by viewModels()
    private lateinit var hotelRoomListAdapter: HotelRoomListAdapter
    private val args: HotelFragmentArgs by navArgs()
    private var hotelId: Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotelId = args.hotelId
        initAdapter()
        observeViewModel()
        viewModel.getHotelInfo(hotelId)
        updateHotelImage()
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

    private fun initBtnClickListener() {
        with(binding) {
            ivRoomFavoriteBtn.setOnClickListener {
                viewModel.hotel.value?.let {
                    if (it.is_liked) viewModel.deleteLikeHotel(hotelId)
                    else viewModel.postLikeHotel(hotelId)

                    updateHotelImage()
                }
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
            ivRoomFavorite.setImageResource(

                if (hotel.is_liked) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
            )
        }
    }

    private fun updateHotelImage() {
        val imageUrl = when (hotelId ) {
            1 -> "https://bit.ly/4bF6NHO"
            2 -> "https://bit.ly/3VbEMCl"
            3 -> "https://bit.ly/3wB5DxY"
            4 -> "https://bit.ly/4dKfFxp"
            else -> "https://bit.ly/3K43Lkm"
        }
        Glide.with(binding.ivHotel)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.ivHotel)
    }

    private fun updateHotelInfoView(hotelInfo: HotelViewModel.Hotel_info) {
        with(binding) {
            tvHotelEventContents.text = bulletSpanText(requireContext(), hotelInfo.event)
            tvPayTossContents.text = bulletSpanText(requireContext(), hotelInfo.pay)
        }
    }

    private fun initAdapter() {
        hotelRoomListAdapter = HotelRoomListAdapter(
            { room ->
                if (room.is_liked) viewModel.deleteLikeRoom(room.room_id)
                else viewModel.postLikeRoom(room.room_id)
            },
            { room ->
                val action = HotelFragmentDirections.actionHotelToRoom(
                    roomId = room.room_id,
                    roomName = room.room_name,
                    price = room.price,
                    startTime = room.start_time,
                    endTime = room.end_time,
                    imageUrl = room.image_url,
                    isLiked = room.is_liked
                )
                findNavController().navigate(action)
            }
        )
        binding.rvRoom.adapter = hotelRoomListAdapter
        binding.rvRoom.layoutManager = LinearLayoutManager(requireContext())
    }

}
