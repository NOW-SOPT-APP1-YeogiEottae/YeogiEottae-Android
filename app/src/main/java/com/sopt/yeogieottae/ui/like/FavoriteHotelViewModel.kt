package com.sopt.yeogieottae.ui.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.data.like.LikeHotelState
import com.sopt.yeogieottae.network.ServicePool
import com.sopt.yeogieottae.network.response.ResponseLikeHotelDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteHotelViewModel : ViewModel() {

    private val likeService by lazy { ServicePool.yeogieottaeService }
    private val _likeHotels = MutableLiveData<List<LikeHotelState>>()
    val likeHotels: LiveData<List<LikeHotelState>> get() = _likeHotels

    fun getLikeHotelInfo() {
        likeService.getLikeHotel().enqueue(object : Callback<ResponseLikeHotelDto> {
            override fun onResponse(
                call: Call<ResponseLikeHotelDto>,
                response: Response<ResponseLikeHotelDto>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        val likeHotelStates = data.result.map { hotel ->
                            LikeHotelState(
                                isSuccess = true,
                                message = data.message,
                                hotelId = hotel.hotelId.toString(),
                                hotelName = hotel.hotelName,
                                reviewRate = hotel.reviewRate.toString(),
                                roomInformation = hotel.roomInformation,
                                roomId = hotel.roomInformation?.roomId?.toString() ?: "",
                                roomName = hotel.roomInformation?.roomName ?: "",
                                roomImage = hotel.roomInformation?.roomImage ?: "",
                                price = hotel.roomInformation?.price
                            )
                        }
                        _likeHotels.value = likeHotelStates
                    } ?: run {
                        _likeHotels.value = listOf(
                            LikeHotelState(
                                isSuccess = false,
                                message = "찜 목록 조회 실패: 데이터가 비어 있습니다."
                            )
                        )
                    }
                } else {
                    _likeHotels.value = listOf(
                        LikeHotelState(
                            isSuccess = false,
                            message = "찜 목록 조회 실패"
                        )
                    )
                }
            }

            override fun onFailure(call: Call<ResponseLikeHotelDto>, t: Throwable) {
                _likeHotels.value = listOf(
                    LikeHotelState(
                        isSuccess = false,
                        message = "찜 목록 서버 조회 실패: ${t.message}"
                    )
                )
            }
        })
    }
}