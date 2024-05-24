package com.sopt.yeogieottae.ui.room

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.databinding.FragmentRoomBinding
import com.sopt.yeogieottae.util.BaseFragment
import com.sopt.yeogieottae.util.bulletSpanText
import com.sopt.yeogieottae.util.bulletSpanTextCancel

class RoomFragment : BaseFragment<FragmentRoomBinding>(
    FragmentRoomBinding::inflate
) {
    private val roomViewModel: RoomViewModel by viewModels()
    private val args: RoomFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgs()
        setupView()
        initBtnEvent()
    }

    private fun initArgs() {
        roomViewModel.setRoomData(
            Room(
                room_id = args.roomId,
                room_name = args.roomName,
                price = args.price,
                start_time = args.startTime,
                end_time = args.endTime,
                image_url = args.imageUrl,
                is_liked = args.isLiked
            )
        )
    }

    private fun setupView() {
        setupRoomName()
        setupRoomPrice()
        setupRoomTimes()
        setupRoomInformation()
        setupRoomFacilities()
        setupRoomCancelPolicy()
        setupRoomImage()
        setupImage()
    }

    private fun setupRoomName() {
        binding.tvRoomDetailRoomName.text = roomViewModel.room.value?.room_name
    }

    private fun setupRoomPrice() {
        val price = roomViewModel.room.value?.price ?: 0
        binding.tvRoomSummaryDiscountPrice.text = getString(R.string.all_price).format(price)
        binding.tvRoomDetailBottomPrice.text = getString(R.string.all_price).format(price)
    }

    private fun setupRoomTimes() {
        val startTime = roomViewModel.room.value?.start_time.orEmpty()
        val endTime = roomViewModel.room.value?.end_time.orEmpty()
        binding.tvRoomSummaryTime.text = getString(R.string.room_in_out).format(startTime, endTime)
    }

    private fun setupRoomInformation() {
        val information = roomViewModel.detail.value?.information ?: emptyList()
        binding.tvRoomDetailNormalInfoDescription.text =
            bulletSpanText(requireContext(), information)
    }

    private fun setupRoomFacilities() {
        val facilities = roomViewModel.detail.value?.facilities ?: emptyList()
        binding.tvRoomDetailFacilitiesDescription.text =
            bulletSpanText(requireContext(), facilities)
    }

    private fun setupRoomCancelPolicy() {
        val cancel = roomViewModel.detail.value?.cancel ?: emptyList()
        binding.tvRoomDetailCancelDescription.text = bulletSpanTextCancel(requireContext(), cancel)
    }

    private fun setupRoomImage() {
        Glide.with(this)
            .load(roomViewModel.room.value?.image_url.orEmpty())
            .placeholder(R.drawable.ic_launcher_foreground) // Placeholder 이미지 설정
            .into(binding.ivRoomDetail)
    }

    private fun initBtnEvent() {
        binding.btnRoomDetailBtn.setOnClickListener {
            if (roomViewModel.room.value?.is_liked ?: true) {
                createSnackBar(
                    binding.root,
                    "Custom Snackbar",
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setOnHotelLike()
                }.show()
                roomViewModel.deleteLikeRoom()
                binding.ivRoomFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                createSnackBar(
                    binding.root,
                    "Custom Snackbar",
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setOffHotelLike()
                }.show()
                roomViewModel.postLikeRoom()
                binding.ivRoomFavorite.setImageResource(R.drawable.ic_favorite_room_on)
            }
        }
    }

    private fun setupImage() {
        binding.ivRoomFavorite.setImageResource(
            if (roomViewModel.room.value?.is_liked
                    ?: true
            ) R.drawable.ic_favorite_room_on else R.drawable.ic_favorite
        )
    }

    private fun createSnackBar(view: View, message: String, duration: Int): Snackbar {
        return Snackbar.make(view, message, duration)
    }

    // 호텔찜 on snackbar
    @SuppressLint("RestrictedApi")
    private fun Snackbar.setOnHotelLike() {
        val customLayout =
            LayoutInflater.from(context).inflate(R.layout.favorite_snackbar_on_layout, null)

        val snackBarLayout = this.view as Snackbar.SnackbarLayout
        snackBarLayout.apply {
            setPadding(0, 0, 0, 30)
            removeAllViews()
            addView(customLayout)

            // 배경색을 투명하게 설정
            background = null
        }
    }

    // off snackbar
    @SuppressLint("RestrictedApi")
    private fun Snackbar.setOffHotelLike() {
        val customLayout =
            LayoutInflater.from(context).inflate(R.layout.favorite_snackbar_off_layout, null)

        val snackBarLayout = this.view as Snackbar.SnackbarLayout
        snackBarLayout.apply {
            setPadding(0, 0, 0, 30)
            removeAllViews()
            addView(customLayout)

            // 배경색을 투명하게 설정
            background = null
        }
    }
}