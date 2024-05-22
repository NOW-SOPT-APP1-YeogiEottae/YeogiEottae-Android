package com.sopt.yeogieottae.data.like

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface LikeService {
    @GET("api/v1/likes")
    fun getLikeHotel(
        @Header("userId") userId: String = "1", // Header에 userId 전달
    ): Call<ResponseLikeHotelDto>
}