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

        // 매진숙소 필터 설정
        initFilterExceptView()
    }

    // 호텔 리사이클러 뷰
    private fun initHotelListView() {
        // 호텔 리사이클러뷰 설정
        val adapter = HotelListAdapter()
        binding.rvSearchlistHotellist.adapter = adapter
        binding.rvSearchlistHotellist.layoutManager = LinearLayoutManager(requireContext())
        hotelViewModel.hotels.observe(viewLifecycleOwner) { hotels ->
            hotels?.let {
                adapter.setHotels(it)
            }
        }
    }

    // 매진 숙소 필터
    private fun initFilterExceptView() {
        binding.rvSearchlistHotellist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var isScrolledUp = false
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && !isScrolledUp) {
                    isScrolledUp = true
                    binding.ivSearchlistFilterExcept.animate()?.alpha(0f)?.setDuration(300)?.start()
                } else if (dy < 0 && isScrolledUp) {
                    isScrolledUp = false
                    binding.ivSearchlistFilterExcept.animate()?.alpha(1f)?.setDuration(300)?.start()
                }
            }
        })
    }
}