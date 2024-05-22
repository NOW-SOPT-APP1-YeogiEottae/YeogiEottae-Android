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
    @SerialName("hotelId")
    val hotelId: Int,
    @SerialName("hotelName")
    val hotelName: String,
    @SerialName("reviewRate")
    val reviewRate: Double,
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