package com.sopt.yeogieottae.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLikeDto(
    @SerialName("code")
    val code: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
)
