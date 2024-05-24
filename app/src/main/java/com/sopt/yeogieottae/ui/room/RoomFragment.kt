package com.sopt.yeogieottae.ui.room

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
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
                roomId = args.roomId,
                roomName = args.roomName,
                price = args.price,
                startTime = args.startTime,
                endTime = args.endTime,
                imageUrl = args.imageUrl,
                isLiked = args.isLiked
            )
        )
    }

    private fun setupView() {
        roomViewModel.room.observe(viewLifecycleOwner) { room ->
            binding.apply {
                tvRoomDetailRoomName.text = room.roomName
                tvRoomSummaryDiscountPrice.text = getString(R.string.all_price).format(room.price)
                tvRoomDetailBottomPrice.text = getString(R.string.all_price).format(room.price)
                tvRoomSummaryTime.text = getString(R.string.room_in_out).format(room.startTime, room.endTime)
                ivRoomDetail.loadImage(room.imageUrl)
                ivRoomFavorite.setImageResource(if (room.isLiked) R.drawable.ic_favorite_room_on else R.drawable.ic_favorite)
            }
        }

        roomViewModel.detail.observe(viewLifecycleOwner) { detail ->
            binding.apply {
                tvRoomDetailNormalInfoDescription.text = bulletSpanText(requireContext(), detail.information)
                tvRoomDetailFacilitiesDescription.text = bulletSpanText(requireContext(), detail.facilities)
                tvRoomDetailCancelDescription.text = bulletSpanTextCancel(requireContext(), detail.cancel)
            }
        }
    }

    private fun View.loadImage(url: String) {
        Glide.with(this@RoomFragment)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(this as ImageView)
    }

    private fun initBtnEvent() {
        binding.btnRoomDetailBtn.setOnClickListener {
            val isLiked = roomViewModel.room.value?.isLiked ?: false
            if (isLiked) {
                showSnackBar("Custom Snackbar", R.layout.favorite_snackbar_off_layout)
                roomViewModel.deleteLikeRoom()
                binding.ivRoomFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                showSnackBar("Custom Snackbar", R.layout.favorite_snackbar_on_layout)
                roomViewModel.postLikeRoom()
                binding.ivRoomFavorite.setImageResource(R.drawable.ic_favorite_room_on)
            }
        }
    }

    private fun showSnackBar(message: String, layoutId: Int) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).apply {
            setCustomLayout(layoutId)
        }.show()
    }

    @SuppressLint("RestrictedApi")
    private fun Snackbar.setCustomLayout(layoutId: Int) {
        val customLayout = LayoutInflater.from(context).inflate(layoutId, null)
        val snackBarLayout = this.view as Snackbar.SnackbarLayout
        snackBarLayout.apply {
            setPadding(0, 0, 0, 30)
            removeAllViews()
            addView(customLayout)
            background = null
        }
    }
}