package com.sopt.yeogieottae.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLikeHotelDto(
    @SerialName("code")
    val code: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: List<LikeHotelInformation>,
)

@Serializable
data class LikeHotelInformation(
    @SerialName("hotelId")
    val hotelId: Int,
    @SerialName("hotelName")
    val hotelName: String,
    @SerialName("reviewRate")
    val reviewRate: Double,
    @SerialName("roomInformation")
    val roomInformation: RoomInformation? = null,
)

@Serializable
data class RoomInformation(
    @SerialName("roomId")
    val roomId: Int,
    @SerialName("roomName")
    val roomName: String,
    @SerialName("roomImage")
    val roomImage: String,
    @SerialName("price")
    val price: Int,
)