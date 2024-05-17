package com.sopt.yeogieottae.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.data.hotel.HotelViewModel
import com.sopt.yeogieottae.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val hotelListAdapter = HotelListAdapter()
    private val hotelViewModel: HotelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 호텔 리사이클러뷰 연결
        initHotelListView()

        // ViewModel의 데이터 관찰
        initObserver()

        // 매진숙소 필터 설정
        initFilterExceptView()
    }

    // 호텔 리사이클러뷰
    private fun initHotelListView() {
        binding.rvSearchlistHotellist.adapter = hotelListAdapter
        binding.rvSearchlistHotellist.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObserver() {
        hotelViewModel.hotels.observe(viewLifecycleOwner) { hotels ->
            hotelListAdapter.setItems(hotels)
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