package com.sopt.yeogieottae.ui.hotel

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.model.Room
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
        initFavoriteButtonClickListener()
    }

    private fun observeViewModel() {
        viewModel.hotel.observe(viewLifecycleOwner) { hotel ->
            hotel?.let {
                updateHotelView(hotel)
                hotelRoomListAdapter.submitList(it.roomList)
            }
        }
        viewModel.hotelInfo.observe(viewLifecycleOwner) { hotelInfo ->
            hotelInfo?.let {
                updateHotelInfoView(it)
            }
        }
    }

    private fun initFavoriteButtonClickListener() {
        binding.ivRoomFavoriteBtn.setOnClickListener {
            viewModel.hotel.value?.let { hotel ->
                toggleFavoriteStatus(hotel.isLiked)
            }
        }
    }

    private fun toggleFavoriteStatus(isLiked: Boolean) {
        if (isLiked) {
            showSnackbarWithLayout(R.layout.favorite_snackbar_off_layout)
            viewModel.deleteLikeHotel(hotelId)
        } else {
            showSnackbarWithLayout(R.layout.favorite_snackbar_on_layout)
            viewModel.postLikeHotel(hotelId)
        }
        updateHotelImage()
    }

    private fun updateHotelView(hotel: HotelViewModel.Hotel) {
        with(binding) {
            tvHotelStar.text = hotel.star
            tvHotelName.text = hotel.name
            tvStarRate.text = hotel.reviewRate.toString()
            tvTotalReview.text = hotel.reviewCount.toString()
            tvMap.text = hotel.location
            ivRoomFavorite.setImageResource(
                if (hotel.isLiked) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
            )
        }
    }

    private fun updateHotelImage() {
        val imageUrl = when (hotelId) {
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

    private fun updateHotelInfoView(hotelInfo: HotelViewModel.HotelInfo) {
        with(binding) {
            tvHotelEventContents.text = bulletSpanText(requireContext(), hotelInfo.event)
            tvPayTossContents.text = bulletSpanText(requireContext(), hotelInfo.pay)
        }
    }

    private fun initAdapter() {
        hotelRoomListAdapter = HotelRoomListAdapter(
            { room ->
                toggleRoomFavoriteStatus(room)
            },
            { room ->
                navigateToRoomDetails(room)
            }
        )
        binding.rvRoom.adapter = hotelRoomListAdapter
        binding.rvRoom.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun toggleRoomFavoriteStatus(room: Room) {
        if (room.isLiked) {
            showSnackbarWithLayout(R.layout.favorite_snackbar_off_layout)
            viewModel.deleteLikeRoom(room.roomId)
        } else {
            showSnackbarWithLayout(R.layout.favorite_snackbar_on_layout)
            viewModel.postLikeRoom(room.roomId)
        }
    }

    private fun navigateToRoomDetails(room: Room) {
        val action = HotelFragmentDirections.actionHotelToRoom(
            roomId = room.roomId,
            roomName = room.roomName,
            price = room.price,
            startTime = room.startTime,
            endTime = room.endTime,
            imageUrl = room.imageUrl,
            isLiked = room.isLiked
        )
        findNavController().navigate(action)
    }

    @SuppressLint("RestrictedApi")
    private fun showSnackbarWithLayout(layoutRes: Int) {
        val customLayout = LayoutInflater.from(context).inflate(layoutRes, null)
        Snackbar.make(binding.root, "", Snackbar.LENGTH_SHORT).apply {
            val snackBarLayout = this.view as Snackbar.SnackbarLayout
            snackBarLayout.setPadding(0, 0, 0, 30)
            snackBarLayout.removeAllViews()
            snackBarLayout.addView(customLayout)
            snackBarLayout.background = null
            show()
        }
    }
}
