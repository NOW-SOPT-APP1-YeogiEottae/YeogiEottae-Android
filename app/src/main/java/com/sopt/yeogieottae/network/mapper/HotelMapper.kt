package com.sopt.yeogieottae.network.mapper

import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.network.response.ResponseHotelDto
import com.sopt.yeogieottae.ui.hotel.HotelViewModel.Hotel

object HotelMapper {
    fun fromDto(dto: ResponseHotelDto.Hotel): Hotel {
        return Hotel(
            name = dto.hotelName,
            star = dto.star,
            location = dto.location,
            reviewRate = dto.reviewRate,
            reviewCount = dto.reviewCount,
            isLiked = dto.isLiked,
            roomList = fromDto(dto.roomList)
        )
    }

    private fun fromDto(dto: List<ResponseHotelDto.Hotel.Room>): List<Room> {
        return dto.map { room ->
            Room(
                roomId = room.roomId,
                roomName = room.roomName,
                price = room.price,
                startTime = room.startTime,
                endTime = room.endTime,
                imageUrl = room.imageUrl,
                isLiked = room.isLiked
            )
        }
    }
}