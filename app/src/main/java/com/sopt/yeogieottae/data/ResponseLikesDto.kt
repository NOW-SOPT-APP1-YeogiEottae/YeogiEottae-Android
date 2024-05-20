package com.sopt.yeogieottae.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLikesDto(
    @SerialName("code")
    val code: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("result")
    val result: Result,
)

@Serializable
data class Result(
    @SerialName("room_likes")
    val roomLikes: List<RoomLike>,
)

@Serializable
data class RoomLike(
    @SerialName("room_information")
    val roomInformation: RoomInformation,
    @SerialName("hotel_id")
    val hotelId: Int,
    @SerialName("hotel_name")
    val hotelName: String,
    @SerialName("review_rate")
    val reviewRate: Double,
)

@Serializable
data class RoomInformation(
    @SerialName("room_id")
    val roomId: Int,
    @SerialName("room_name")
    val roomName: String,
    @SerialName("room_image")
    val roomImage: String,
    @SerialName("price")
    val price: Int,
)