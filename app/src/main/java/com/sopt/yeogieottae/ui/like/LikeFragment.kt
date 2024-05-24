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

class LikeFragment: BaseFragment<FragmentLikeBinding>(
    FragmentLikeBinding::inflate
){
    private var _binding: FragmentLikeBinding? = null
    private val favoriteHotelViewModel by viewModels<FavoriteHotelViewModel>()
    private val favoriteHotelListAdapter = FavoriteHotelListAdapter { hotel ->
        showPopup()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViews()
        initTitleView()
        initObserver()
        initClickListner()
    }

    private fun initRecyclerViews() {
        // 초기 팝업 상태를 invisible로 설정
        hidePopup()

        // RecyclerView 설정
        binding.rvLikeHotellist.apply {
            adapter = favoriteHotelListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        // ViewModel의 데이터 관찰
        favoriteHotelViewModel.likehotels.observe(viewLifecycleOwner) { hotels ->
            favoriteHotelListAdapter.setItems(hotels)
        }
    }

    private fun initObserver() {
        favoriteHotelViewModel.getLikeHotelInfo()

        favoriteHotelViewModel.likehotels.observe(viewLifecycleOwner) { hotels ->
            favoriteHotelListAdapter.setItems(hotels)
        }
    }

    private fun initClickListner() {
        binding.vLikePopupBg.setOnClickListener {
            hidePopup()
        }

        binding.ivCompareBtn.setOnClickListener {
            findNavController().navigate(R.id.fragment_compare)
        }
    }

    @SuppressLint("ResourceType")
    private fun showPopup() {
        // 애니메이션 팝업에 적용
        val animation = AnimationUtils.loadAnimation(requireContext(), R.drawable.bottom_sheet_anim)
        binding.loLikePopupBottom.startAnimation(animation)

        // 팝업을 표시
        binding.loLikePopup.visibility = View.VISIBLE
    }

    private fun hidePopup() {
        binding.loLikePopup.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 상단 바
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