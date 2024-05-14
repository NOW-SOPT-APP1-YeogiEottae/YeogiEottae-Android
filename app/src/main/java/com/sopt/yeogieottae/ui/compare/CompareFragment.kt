package com.sopt.yeogieottae.ui.compare

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.yeogieottae.data.Hotel
import com.sopt.yeogieottae.databinding.FragmentCompareBinding
import com.sopt.yeogieottae.util.BaseFragment

class CompareFragment : BaseFragment<FragmentCompareBinding>(
    FragmentCompareBinding::inflate
) {

    private val hotels = listOf(
        Hotel("Hotel 1", "400,000원", 4.6f, "부대시설: 이런거 저런거 있음"),
        Hotel("Hotel 2", "300,000원", 4.2f, "부대시설: 이런거 저런거 있음"),
        Hotel("Hotel 3", "400,000원", 4.6f, "부대시설: 이런거 저런거 있음"),
        Hotel("Hotel 4", "300,000원", 4.2f, "부대시설: 이런거 저런거 있음"),
        Hotel("Hotel 5", "400,000원", 4.6f, "부대시설: 이런거 저런거 있음"),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCompare.apply {
            adapter = HotelAdapter(hotels)
        }
    }
}