package com.sopt.yeogieottae.network.service

import com.sopt.yeogieottae.network.response.ResponseHotelDto
import com.sopt.yeogieottae.network.response.ResponseLikeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YeogieottaeService {

    @GET("/api/v1/hotels/{hotelId}")
    suspend fun getHotel(@Path("hotelId") hotelId: Int): Response<ResponseHotelDto>

    suspend fun postLike(@Query("roomType") roomType: Int): Response<ResponseLikeDto>
}
