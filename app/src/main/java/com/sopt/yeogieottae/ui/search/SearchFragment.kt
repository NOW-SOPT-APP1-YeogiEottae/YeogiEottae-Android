package com.sopt.yeogieottae.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.databinding.FragmentSearchBinding
import com.sopt.yeogieottae.util.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var hotelListAdapter: SearchHotelListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initRecyclerView()
        observeViewModel()
        handleRecyclerViewScroll()
    }

    private fun initAdapter() {
        hotelListAdapter = SearchHotelListAdapter(searchViewModel) { hotelId ->
            navigateToHotel(hotelId)
        }
    }

    private fun initRecyclerView() {
        binding.rvSearchlistHotellist.apply {
            adapter = hotelListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeViewModel() {
        searchViewModel.hotels.observe(viewLifecycleOwner) { hotels ->
            hotelListAdapter.submitList(hotels)
        }
    }

    private fun handleRecyclerViewScroll() {
        binding.rvSearchlistHotellist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var isScrolledUp = false
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.ivSearchlistFilterExcept.animate().apply {
                    duration = 300
                    alpha(if (dy > 0 && !isScrolledUp) {
                        isScrolledUp = true
                        0f
                    } else if (dy < 0 && isScrolledUp) {
                        isScrolledUp = false
                        1f
                    } else {
                        return
                    }).start()
                }
            }
        })
    }

    private fun navigateToHotel(hotelId: Int) {
        val action = SearchFragmentDirections.actionSearchToHotel(hotelId)
        findNavController().navigate(action)
    }
}