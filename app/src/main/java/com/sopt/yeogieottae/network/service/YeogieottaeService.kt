package com.sopt.yeogieottae.network.service

import com.sopt.yeogieottae.network.response.ResponseHotelDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface YeogieottaeService {

    @GET("/api/v1/hotels/{hotelId}")
    suspend fun getHotel(@Path("hotelId") hotelId: Int): Response<ResponseHotelDto>
}
