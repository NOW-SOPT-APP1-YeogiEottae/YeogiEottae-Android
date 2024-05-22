package com.sopt.yeogieottae.ui.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.data.like.LikeHotelState
import com.sopt.yeogieottae.network.response.ResponseLikeHotelDto
import com.sopt.yeogieottae.network.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteHotelViewModel : ViewModel() {
    private val likeService by lazy { ServicePool.likeService }
    private val _likehotels = MutableLiveData<List<LikeHotelState>>()
    val likehotels: LiveData<List<LikeHotelState>> get() = _likehotels
    fun getLikeHotelInfo() {
        likeService.getLikeHotel().enqueue(object : Callback<ResponseLikeHotelDto> {
            override fun onResponse(
                call: Call<ResponseLikeHotelDto>,
                response: Response<ResponseLikeHotelDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        val likeHotelStates = data.result.map { hotel ->
                            val roomInformation = hotel.roomInformation
                            LikeHotelState(
                                isSuccess = true,
                                message = data.message,
                                hotelId = hotel.hotelId.toString(),
                                hotelName = hotel.hotelName,
                                reviewRate = hotel.reviewRate.toString(),
                                roomInformation = roomInformation,
                                roomId = roomInformation?.roomId?.toString() ?: "",
                                roomName = roomInformation?.roomName ?: "",
                                roomImage = roomInformation?.roomImage ?: "",
                                price = roomInformation?.price
                            )
                        }
                        _likehotels.value = likeHotelStates
                    } ?: run {
                        _likehotels.value = listOf(
                            LikeHotelState(
                                isSuccess = false,
                                message = "찜 목록 조회 실패: 데이터가 비어 있습니다."
                            )
                        )
                    }
                } else {
                    _likehotels.value = listOf(
                        LikeHotelState(
                            isSuccess = false,
                            message = "찜 목록 조회 실패"
                        )
                    )
                }
            }

            override fun onFailure(call: Call<ResponseLikeHotelDto>, t: Throwable) {
                _likehotels.value = listOf(
                    LikeHotelState(
                        isSuccess = false,
                        message = "찜 목록 서버 조회 실패: ${t.message}"
                    )
                )
            }
        }
        )
    }
}