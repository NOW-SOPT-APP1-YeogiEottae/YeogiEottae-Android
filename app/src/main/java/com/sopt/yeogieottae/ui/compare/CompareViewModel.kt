package com.sopt.yeogieottae.ui.compare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.data.Room
/*
class CompareViewModel : ViewModel() {
    private val _apiResponse = MutableLiveData<ApiResponse>()
    val apiResponse: LiveData<ApiResponse> get() = _apiResponse

    init {
        setApiResponse(isEmpty = true)
    }

    fun setApiResponse(isEmpty: Boolean) {
        _apiResponse.value = if (isEmpty) {
            ApiResponse(
                code = 200,
                success = true,
                message = "비교하기 목록 불러오기를 성공했습니다.",
                data = emptyList()
            )
        } else {
            ApiResponse(
                code = 200,
                success = true,
                message = "비교하기 목록 불러오기를 성공했습니다.",
                data = listOf(
                    Room(
                        roomId = 1,
                        hotelName = "그랜드 인터컨티넨탈 파르나스",
                        roomType = "클래식 킹",
                        price = 464640,
                        reviewRate = 9.4,
                        reviewCount = 2183,
                        imageUrl = "image1"
                    ),
                    Room(
                        roomId = 2,
                        hotelName = "그랜드 인터컨티넨탈 파르나스",
                        roomType = "주니어 스위트 킹",
                        price = 608679,
                        reviewRate = 9.4,
                        reviewCount = 2183,
                        imageUrl = "image2"
                    ),
                    Room(
                        roomId = 3,
                        hotelName = "서울 신라 호텔",
                        roomType = "디럭스 트윈",
                        price = 497000,
                        reviewRate = 9.8,
                        reviewCount = 1183,
                        imageUrl = "image3"
                    ),
                    Room(
                        roomId = 4,
                        hotelName = "서울 신라 호텔",
                        roomType = "디럭스 트윈",
                        price = 497000,
                        reviewRate = 9.8,
                        reviewCount = 1183,
                        imageUrl = "image3"
                    ),
                    Room(
                        roomId = 5,
                        hotelName = "서울 신라 호텔",
                        roomType = "디럭스 트윈",
                        price = 497000,
                        reviewRate = 9.8,
                        reviewCount = 1183,
                        imageUrl = "image3"
                    ),
                    Room(
                        roomId = 6,
                        hotelName = "서울 신라 호텔",
                        roomType = "디럭스 트윈",
                        price = 497000,
                        reviewRate = 9.8,
                        reviewCount = 1183,
                        imageUrl = "image3"
                    )
                )
            )
        }
    }
}*/