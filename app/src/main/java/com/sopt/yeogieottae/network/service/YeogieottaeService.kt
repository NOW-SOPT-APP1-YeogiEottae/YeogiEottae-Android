package com.sopt.yeogieottae.network.service

import com.sopt.yeogieottae.network.request.RequestLikeDto
import com.sopt.yeogieottae.network.response.ResponseHotelDto
import com.sopt.yeogieottae.network.response.ResponseLikeDto
import com.sopt.yeogieottae.network.response.ResponseLikeHotelDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface YeogieottaeService {

    @GET("/api/v1/hotels/{hotelId}")
    suspend fun getHotel(@Path("hotelId") hotelId: Int): Response<ResponseHotelDto>

    @POST("/api/v1/likes")
    suspend fun postLike(
        @Query("roomType") roomType: Int,
        @Body requestLikeDto: RequestLikeDto
    ): Response<ResponseLikeDto>

    @HTTP(method="DELETE", hasBody=true, path="/api/v1/likes")
    suspend fun deleteLike(
        @Query("roomType") roomType: Int,
        @Body requestLikeDto: RequestLikeDto
    ): Response<ResponseLikeDto>

    @GET("api/v1/likes")
    fun getLikeHotel(): Call<ResponseLikeHotelDto>
}