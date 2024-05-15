package com.sopt.yeogieottae.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.data.hotel.HotelViewModel
import com.sopt.yeogieottae.databinding.FragmentSearchBinding
import com.sopt.yeogieottae.util.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {
    private val hotelViewModel: HotelViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 호텔 리사이클러뷰 연결
        initHotelListView()
    }

    private fun initHotelListView() {
        // 호텔 리사이클러뷰 설정
        val recyclerView: RecyclerView = requireView().findViewById(R.id.rv_searchlist_hotellist)
        val adapter = HotelListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // HotelViewModel에서 호텔 목록을 관찰하고 변경되면 업데이트
        hotelViewModel.hotels.observe(viewLifecycleOwner) { hotels ->
            hotels?.let {
                adapter.setHotels(it)
            }
        }
    }
}