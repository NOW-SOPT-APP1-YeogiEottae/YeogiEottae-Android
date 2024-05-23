package com.sopt.yeogieottae.network.service

import com.sopt.yeogieottae.network.request.RequestDeleteRoomId
import com.sopt.yeogieottae.network.request.RequestRoomId
import com.sopt.yeogieottae.network.response.ResponseCompareDto
import com.sopt.yeogieottae.network.response.ResponseCompareLikesDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface YeogieottaeService {
    @GET("/api/v1/compare-rooms")
    suspend fun compare(): Response<ResponseCompareDto>

    @GET("/api/v1/compare-rooms/likes")
    suspend fun getCompare(): Response<ResponseCompareLikesDto>

    @POST("/api/v1/compare-rooms/likes")
    suspend fun postCompare(@Body roomIdRequest: RequestRoomId): Response<Unit>

    @HTTP(method="DELETE", hasBody=true, path="/api/v1/compare-rooms/likes")
    suspend fun deleteCompare(@Body deleteRoomIdRequest: RequestDeleteRoomId): Response<Unit>
}