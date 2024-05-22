package com.sopt.yeogieottae.ui.hotel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.network.ServicePool
import com.sopt.yeogieottae.network.mapper.HotelMapper
import com.sopt.yeogieottae.network.request.RequestLikeDto
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

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

    fun postLikeHotel(hotelId: Int) {
        viewModelScope.launch {
            runCatching {
                ServicePool.yeogieottaeService.postLike(
                    0,
                    requestLikeDto = RequestLikeDto(id = hotelId)
                )
            }.onSuccess { response ->
                response.body()?.message.let {
                    _hotel.value=_hotel.value?.copy(is_liked = true)
                    Log.d("Hotel- postLike", "postLike: $it")
                }

            }.onFailure { e ->
                Log.d("Hotel- postLike", "setLikeHotel: ${e.message}")
            }
        }
    }

    fun deleteLikeHotel(hotelId:Int){
        viewModelScope.launch {
            runCatching {
                ServicePool.yeogieottaeService.deleteLike(
                    0,
                    requestLikeDto = RequestLikeDto(id = hotelId)
                )
            }.onSuccess { response ->
                response.body()?.let {
                    Log.d("Hotel- delLike", "deleteLike: $it")
                }
                _hotel.value=_hotel.value?.copy(is_liked = false)
            }.onFailure { e ->
                if(e is HttpException) Log.d("Hotel- delLike", "deleteLikeError Http:${e.message} ")
                Log.d("Hotel- delLike", "deleteLikeError: ${e}")
                Log.d("Hotel- delLike", "deleteLikeError Message: ${e.message}")
            }
        }
    }

    fun postLikeRoom(roomId: Int) {
        viewModelScope.launch {
            runCatching {
                ServicePool.yeogieottaeService.postLike(1, requestLikeDto = RequestLikeDto(roomId))
            }.onSuccess { response ->
                response.body()?.message.let {
                    Log.d("postLike", "postLike: $it")
                }
            }
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