package com.sopt.yeogieottae.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentSearchBinding
import com.sopt.yeogieottae.util.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {
    private lateinit var hotelListAdapter: SearchHotelListAdapter
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //hotelListAdapter 연결
        initAdapter()

        // 호텔 리사이클러뷰 연결
        initHotelListView()

        // ViewModel의 데이터 관찰
        initObserver()

        // 매진숙소 필터 설정
        initFilterExceptView()
    }

    private fun initAdapter() {
        hotelListAdapter = SearchHotelListAdapter(searchViewModel) { hotelId ->
            navigateToHotel(hotelId)
        }
    }

    // 호텔 리사이클러뷰
    private fun initHotelListView() {
        binding.rvSearchlistHotellist.adapter = hotelListAdapter
        binding.rvSearchlistHotellist.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObserver() {
        searchViewModel.hotels.observe(viewLifecycleOwner) { hotels ->
            hotels?.let {
                hotelListAdapter.submitList(hotels)
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

    fun navigateToHotel(hotelId: Int) {
        val action = SearchFragmentDirections.actionSearchToHotel(hotelId)
        findNavController().navigate(action)
    }
}