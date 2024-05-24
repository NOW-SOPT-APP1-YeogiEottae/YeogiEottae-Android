package com.sopt.yeogieottae.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.yeogieottae.network.ServicePool
import com.sopt.yeogieottae.network.request.RequestLikeDto
import com.sopt.yeogieottae.network.response.Hotel
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _hotels = MutableLiveData<List<Hotel>>()
    val hotels: LiveData<List<Hotel>> get() = _hotels

    init {
        fetchHotels()
    }

    private fun fetchHotels() {
        _hotels.value = listOf(
            Hotel(
                hotelId = 1,
                hotelName = "그랜드 인터컨티넨탈 파르나스",
                location = "삼성역 도보 5분",
                type = "호텔",
                reviewRate = 9.4,
                reviewCount = 2183,
                price = 464640,
                imageUrl = "https://bit.ly/4bF6NHO",
                isLiked = false
            ),
            Hotel(
                hotelId = 2,
                hotelName = "서울 신라 호텔",
                location = "영등포역 도보 3분",
                type = "호텔",
                reviewRate = 9.8,
                reviewCount = 1183,
                price = 497000,
                imageUrl = "https://bit.ly/3VbEMCl",
                isLiked = false
            ),
            Hotel(
                hotelId = 3,
                hotelName = "노보텔 앰버서더 용산",
                location = "신용산역 도보 3분",
                type = "호텔",
                reviewRate = 9.4,
                reviewCount = 2183,
                price = 287000,
                imageUrl = "https://bit.ly/3wB5DxY",
                isLiked = false
            ),
            Hotel(
                hotelId = 4,
                hotelName = "그랜드 인터컨티넨탈 파르나스",
                location = "건대입구역 도보 3분",
                type = "호텔",
                reviewRate = 9.4,
                reviewCount = 3183,
                price = 159000,
                imageUrl = "https://bit.ly/4dKfFxp",
                isLiked = false
            ),
            Hotel(
                hotelId = 5,
                hotelName = "앰버서더 서울 풀만 호텔",
                location = "성수역 도보 3분",
                type = "호텔",
                reviewRate = 9.2,
                reviewCount = 2003,
                price = 586850,
                imageUrl = "https://bit.ly/3K43Lkm",
                isLiked = false
            )
        )
    }

    fun postLikeHotel(hotelId: Int) {
        updateLikeStatus(hotelId, true)
    }

    fun deleteLikeHotel(hotelId: Int) {
        updateLikeStatus(hotelId, false)
    }

    private fun updateLikeStatus(hotelId: Int, isLiked: Boolean) {
        viewModelScope.launch {
            runCatching {
                if (isLiked) {
                    ServicePool.yeogieottaeService.postLike(0, RequestLikeDto(id = hotelId))
                } else {
                    ServicePool.yeogieottaeService.deleteLike(0, RequestLikeDto(id = hotelId))
                }
            }.onSuccess {
                _hotels.value = _hotels.value?.map { hotel ->
                    if (hotel.hotelId == hotelId) hotel.copy(isLiked = isLiked) else hotel
                }
                Log.d("Hotel - ${if (isLiked) "post" else "delete"}Like", "Like status updated for hotelId: $hotelId")
            }.onFailure { e ->
                Log.e("Hotel - ${if (isLiked) "post" else "delete"}Like", "Error updating like status: ${e.message}", e)
            }
        }
    }
}