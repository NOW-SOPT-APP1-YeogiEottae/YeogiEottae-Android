package com.sopt.yeogieottae.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCompareLikesDto(
    @SerialName("code")
    val code: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Result,
)

@Serializable
data class Result(
    @SerialName("roomList")
    val roomList: List<CompareLikesRoom>,
)

@Serializable
data class CompareLikesRoom(
    @SerialName("roomId")
    val roomId: Int,
    @SerialName("hotelName")
    val hotelName: String,
    @SerialName("roomName")
    val roomName: String,
    @SerialName("location")
    val location: String,
    @SerialName("imageUrl")
    val imageUrl: String,
)