package com.sopt.yeogieottae.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestRoomId(
    @SerialName("roomId")
    val roomId: List<Int>,
)