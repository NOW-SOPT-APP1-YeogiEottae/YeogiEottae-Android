package com.sopt.yeogieottae.ui.hotel

import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.view.View
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentHotelBinding
import com.sopt.yeogieottae.util.BaseFragment

class HotelFragment : BaseFragment<FragmentHotelBinding>(
    FragmentHotelBinding::inflate
) {
    private val viewModel: HotelViewModel by viewModels()
    private lateinit var hotelRoomListAdapter: HotelRoomListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        viewModel.hotel.observe(viewLifecycleOwner) {
            hotelRoomListAdapter.submitList(it.room_list)
        }
        viewModel.initValue()
        initView()
    }

    private fun initView() {
        with(binding) {
            tvHotelStar.text = viewModel.hotel.value?.star
            tvHotelName.text = viewModel.hotel.value?.name
            tvStarRate.text = viewModel.hotel.value?.review_rate.toString()
            tvTotalReview.text = viewModel.hotel.value?.review_count.toString()
            tvMap.text = viewModel.hotel.value?.location
            tvHotelEventContents.text =
                createSpannedText(viewModel.hotel_info.value?.event ?: emptyList())
            tvPayTossContents.text =
                createSpannedText(viewModel.hotel_info.value?.pay ?: emptyList())

        }
    }

    private fun initAdapter() {
        hotelRoomListAdapter = HotelRoomListAdapter(requireContext())
        binding.rvRoom.adapter = hotelRoomListAdapter
        binding.rvRoom.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun createSpannedText(
        texts: List<String>,
        bulletColor: Int = getColor(requireContext(), R.color.gray_700)
    ): SpannableStringBuilder {
        val spannableStringBuilder = SpannableStringBuilder()

        for ((index, text) in texts.withIndex()) {
            val spannableString = SpannableString(text)
            val bulletSpan = BulletSpan(6, bulletColor, 1)

            spannableString.setSpan(
                bulletSpan,
                0,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.append(spannableString)
            if (index < texts.size - 1) {
                spannableStringBuilder.append("\n")
            }
        }
        return spannableStringBuilder
    }
}