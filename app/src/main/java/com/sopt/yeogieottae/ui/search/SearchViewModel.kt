package com.sopt.yeogieottae.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.network.response.Hotel

class SearchViewModel : ViewModel() {
    private val _hotels = MutableLiveData<List<Hotel>>()
    val hotels: LiveData<List<Hotel>> get() = _hotels

    init {
        fetchHotelResponse()
    }

    fun fetchHotelResponse() {
        val hotelData = listOf(
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
        _hotels.value = hotelData
    }
}