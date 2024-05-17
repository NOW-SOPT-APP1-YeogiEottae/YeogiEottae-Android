package com.sopt.yeogieottae.ui.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.data.model.Room

class HotelViewModel : ViewModel() {

    private val _hotel = MutableLiveData<Hotel>()
    val hotel: LiveData<Hotel>
        get() = _hotel

    private val _hotel_info = MutableLiveData<Hotel_info>()
    val hotel_info: LiveData<Hotel_info>
        get() = _hotel_info


    fun initValue() {
        _hotel.value = Hotel(
            room_list = listOf(
                Room(
                    room_id = 1,
                    room_name = "스탠다드 트윈룸",
                    price = 156900,
                    start_time = "15:00",
                    end_time = "11:00",
                    image_url = "",
                    is_liked = false
                ),
                Room(
                    room_id = 2,
                    room_name = "스탠다드 트윈룸",
                    price = 156900,
                    start_time = "15:00",
                    end_time = "11:00",
                    image_url = "",
                    is_liked = false
                )
            )
        )
        _hotel_info.value = Hotel_info(
            pay = listOf(
                "3만원 이상 10% 5천원 캐시백 (오전 10시, 일 300...",
                "2만원 이상, 2천원 할인 (오후 4시, 일 800명)",
                "+생애 첫결제 시,5천원 캐시백"
            ),
            event = listOf(
                "자연을 아끼는 마음을 담은 [그린 호캉스] ",
                "시간 가는 줄 모르는 #Let’s Puzzle",
                "아이들이 좋아하는 #티니핑월드"
            )
        )
    }

    data class Hotel(
        val name: String = "나인트리 프리미어 호텔",
        val star: String = "5성급",
        val location: String = "경기 성남시 수정구 시흥동 296-3",
        val review_rate: Double = 9.3,
        val review_count: Int = 800,
        val is_liked: Boolean = true,
        val room_list: List<Room> = emptyList()
    )

    data class Hotel_info(
        val pay: List<String>,
        val event: List<String>
    )
}