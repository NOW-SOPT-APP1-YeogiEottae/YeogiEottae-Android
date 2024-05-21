package com.sopt.yeogieottae.ui.hotel

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.network.ServicePool
import com.sopt.yeogieottae.network.mapper.HotelMapper
import kotlinx.coroutines.launch
import org.json.JSONObject

class HotelViewModel : ViewModel() {

    private val _hotel = MutableLiveData<Hotel>()
    val hotel: LiveData<Hotel>
        get() = _hotel

    private val _hotel_info = MutableLiveData<Hotel_info>()
    val hotel_info: LiveData<Hotel_info>
        get() = _hotel_info


    init {
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

    fun setLike() {
        _hotel.value = _hotel.value?.let { hotel ->
            hotel.copy(is_liked = !hotel.is_liked)
        }
    }


    fun getHotelInfo(hotelId: Int) {
        viewModelScope.launch {
            runCatching {
                ServicePool.yeogieottaeService.getHotel(hotelId)
            }.onSuccess { response ->
                response.body()?.result?.let {
                    _hotel.value = HotelMapper.fromDto(it)
                    Log.d("getHotel", "_hotel정보여유: ${_hotel.value}")
                }
            }.onFailure { e ->
                val error = e.message
                val errorJson = JSONObject(error)
                val errorMessage = errorJson.getString("message")
                Log.d("", "getHotelInfo: $errorMessage")
            }
        }
    }

    data class Hotel(
        val name: String,
        val star: String,
        val location: String,
        val review_rate: Float,
        val review_count: Int,
        val is_liked: Boolean,
        val room_list: List<Room>
    )


    data class Hotel_info(
        val pay: List<String>,
        val event: List<String>
    )
}