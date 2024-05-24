package com.sopt.yeogieottae.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCompareDto(
    @SerialName("code")
    val code: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: List<ResponseCompareRoom>,
)

@Serializable
data class ResponseCompareRoom(
    @SerialName("roomId")
    val roomId: Int,
    @SerialName("hotelName")
    val hotelName: String,
    @SerialName("roomName")
    val roomName: String,
    @SerialName("price")
    val price: Int,
    @SerialName("reviewRate")
    val reviewRate: Float,
    @SerialName("reviewCount")
    val reviewCount: Float,
    @SerialName("imageUrl")
    val imageUrl: String,
)