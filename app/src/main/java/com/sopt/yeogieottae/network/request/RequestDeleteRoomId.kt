package com.sopt.yeogieottae.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDeleteRoomId(
    @SerialName("roomId")
    val roomId: Int,
)