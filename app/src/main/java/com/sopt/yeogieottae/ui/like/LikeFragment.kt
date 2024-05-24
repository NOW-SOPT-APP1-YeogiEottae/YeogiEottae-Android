package com.sopt.yeogieottae.ui.like

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.yeogieottae.R
import com.sopt.yeogieottae.databinding.FragmentLikeBinding
import com.sopt.yeogieottae.util.BaseFragment

class LikeFragment : BaseFragment<FragmentLikeBinding>(
    FragmentLikeBinding::inflate
) {
    private val favoriteHotelViewModel by viewModels<FavoriteHotelViewModel>()
    private val favoriteHotelListAdapter = FavoriteHotelListAdapter { showPopup() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObserver()
        initClickListener()
    }

    private fun initRecyclerView() {
        hidePopup()
        binding.rvLikeHotellist.apply {
            adapter = favoriteHotelListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initObserver() {
        favoriteHotelViewModel.getLikeHotelInfo()
        favoriteHotelViewModel.likeHotels.observe(viewLifecycleOwner) { hotels ->
            favoriteHotelListAdapter.setItems(hotels)
        }
    }

    private fun initClickListener() {
        binding.vLikePopupBg.setOnClickListener {
            hidePopup()
        }
        binding.ivCompareBtn.setOnClickListener {
            findNavController().navigate(R.id.fragment_compare)
        }
    }

    @SuppressLint("ResourceType")
    private fun showPopup() {
        val animation = AnimationUtils.loadAnimation(requireContext(), R.drawable.bottom_sheet_anim)
        binding.loLikePopupBottom.startAnimation(animation)
        binding.loLikePopup.visibility = View.VISIBLE
    }

    private fun hidePopup() {
        binding.loLikePopup.visibility = View.INVISIBLE
    }

    @SuppressLint("ResourceType")
    private fun initTitleView() {
        binding.rvLikeHotellist.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var isScrolledUp = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && !isScrolledUp) {
                    isScrolledUp = true
                    binding.loLikeTitleDate.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .withEndAction { binding.loLikeTitleDate.visibility = View.GONE }
                        .start()
                } else if (dy < 0 && isScrolledUp) {
                    isScrolledUp = false
                    binding.loLikeTitleDate.visibility = View.VISIBLE
                    binding.loLikeTitleDate.animate()
                        .alpha(1f)
                        .setDuration(300)
                        .start()
                }
            }
        })
    }
}