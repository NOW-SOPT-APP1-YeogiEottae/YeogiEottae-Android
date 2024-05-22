package com.sopt.yeogieottae.network.service

import com.sopt.yeogieottae.network.response.ResponseLikeHotelDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface YeogieottaeService {
    @GET("api/v1/likes")
    fun getLikeHotel(
        @Header("userId") userId: String = "1", // Header에 userId 전달
    ): Call<ResponseLikeHotelDto>
}