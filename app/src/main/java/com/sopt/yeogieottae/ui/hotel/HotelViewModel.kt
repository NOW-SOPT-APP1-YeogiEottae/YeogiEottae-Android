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

class HotelViewModel : ViewModel() {

    private val _hotel = MutableLiveData<Hotel>()
    val hotel: LiveData<Hotel>
        get() = _hotel

    private val _hotelInfo = MutableLiveData<HotelInfo>()
    val hotelInfo: LiveData<HotelInfo>
        get() = _hotelInfo

    init {
        _hotelInfo.value = HotelInfo(
            pay = listOf(
                "3만원 이상 10% 5천원 캐시백 (오전 10시, 일 300...",
                "2만원 이상, 2천원 할인 (오후 4시, 일 800명)",
                "+생애 첫결제 시, 5천원 캐시백"
            ),
            event = listOf(
                "자연을 아끼는 마음을 담은 [그린 호캉스]",
                "시간 가는 줄 모르는 #Let’s Puzzle",
                "아이들이 좋아하는 #티니핑월드"
            )
        )
    }

    fun postLikeHotel(hotelId: Int) {
        handleLikeAction(hotelId, true, 0) {
            _hotel.value = _hotel.value?.copy(isLiked = true)
        }
    }

    fun deleteLikeHotel(hotelId: Int) {
        handleLikeAction(hotelId, false, 0) {
            _hotel.value = _hotel.value?.copy(isLiked = false)
        }
    }

    fun postLikeRoom(roomId: Int) {
        handleLikeAction(roomId, true, 1) {
            val updatedRoomList = updateRoomLikeStatus(roomId, true)
            _hotel.value = _hotel.value?.copy(roomList = updatedRoomList ?: listOf())
        }
    }

    fun deleteLikeRoom(roomId: Int) {
        handleLikeAction(roomId, false, 1) {
            val updatedRoomList = updateRoomLikeStatus(roomId, false)
            _hotel.value = _hotel.value?.copy(roomList = updatedRoomList ?: listOf())
        }
    }

    private fun handleLikeAction(id: Int, isLike: Boolean, type: Int, onSuccessAction: () -> Unit) {
        viewModelScope.launch {
            runCatching {
                if (isLike) {
                    ServicePool.yeogieottaeService.postLike(type, RequestLikeDto(id))
                } else {
                    ServicePool.yeogieottaeService.deleteLike(type, RequestLikeDto(id))
                }
            }.onSuccess {
                onSuccessAction()
                Log.d("Hotel-LikeAction", "Action success for id: $id")
            }.onFailure { e ->
                Log.e("Hotel-LikeAction", "Action failed: ${e.message}")
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
                }
            }.onFailure { e ->
                Log.e("Hotel-GetInfo", "Failed to get hotel info: ${e.message}")
            }
        }
    }

    private fun updateRoomLikeStatus(roomId: Int, isLiked: Boolean): List<Room>? {
        return _hotel.value?.roomList?.map { room ->
            if (room.roomId == roomId) {
                room.copy(isLiked = isLiked)
            } else {
                room
            }
        }
    }

    data class Hotel(
        val name: String,
        val star: String,
        val location: String,
        val reviewRate: Float,
        val reviewCount: Int,
        val isLiked: Boolean,
        val roomList: List<Room>
    )

    data class HotelInfo(
        val pay: List<String>,
        val event: List<String>
    )
}