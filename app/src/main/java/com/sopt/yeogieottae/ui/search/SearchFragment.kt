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

    // 매진 숙소 필터
    private fun initFilterExceptView() {
        // RecyclerView 스크롤 이벤트 처리
        val recyclerView: RecyclerView = requireView().findViewById(R.id.rv_searchlist_hotellist)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var isScrolledUp = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 스크롤 방향에 따라 아래 이미지뷰 숨기기 또는 표시하기
                if (dy > 0 && !isScrolledUp) {
                    // 아래로 스크롤할 때
                    // 아래 이미지뷰 숨기기
                    isScrolledUp = true
                    view?.findViewById<View>(R.id.iv_searchlist_filter_except)?.animate()?.alpha(0f)
                        ?.setDuration(300)?.start()
                } else if (dy < 0 && isScrolledUp) {
                    // 위로 스크롤할 때
                    // 아래 이미지뷰 표시하기
                    isScrolledUp = false
                    view?.findViewById<View>(R.id.iv_searchlist_filter_except)?.animate()?.alpha(1f)
                        ?.setDuration(300)?.start()
                }
            }
        })
    }
}