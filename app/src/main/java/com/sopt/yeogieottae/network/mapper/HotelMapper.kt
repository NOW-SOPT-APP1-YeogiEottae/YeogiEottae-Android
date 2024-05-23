package com.sopt.yeogieottae.network.mapper

import com.sopt.yeogieottae.data.model.Room
import com.sopt.yeogieottae.network.response.ResponseHotelDto
import com.sopt.yeogieottae.ui.hotel.HotelViewModel.Hotel

object HotelMapper {
    fun fromDto(dto: ResponseHotelDto.Hotel): Hotel {
        return Hotel(
            name = dto.hotel_name,
            star = dto.star,
            location = dto.location,
            review_rate = dto.review_rate,
            review_count = dto.review_count,
            is_liked = dto.is_liked,
            room_list = fromDto(dto.room_list)
        )
    }

    fun fromDto(dto: List<ResponseHotelDto.Hotel.Room>): List<Room> {
        return dto.map { room ->
            Room(
                room_id = room.room_id,
                room_name = room.room_name,
                price = room.price,
                start_time = room.start_time,
                end_time = room.end_time,
                image_url = room.image_url,
                is_liked = room.is_liked
            )
        }
    }
}
