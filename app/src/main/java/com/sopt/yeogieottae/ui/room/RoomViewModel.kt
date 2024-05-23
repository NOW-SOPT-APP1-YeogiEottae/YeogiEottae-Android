package com.sopt.yeogieottae.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.yeogieottae.data.model.Room

class RoomViewModel : ViewModel() {
    private val _room = MutableLiveData<Room>()
    val room: LiveData<Room>
        get() = _room

    private val _detail = MutableLiveData<Detail>()
    val detail: LiveData<Detail>
        get() = _detail

    init {
        _detail.value = Detail(
            information = listOf(
                "2인 기준 최대 3인 (유료)",
                "인원 추가 시 비용이 발생돼요.",
                "싱글베드 2개",
                "객실+욕실 / 12평",
            ),
            facilities = listOf(
                "TV, 쇼파, 티테이블, 사무용책상, 옷장, 에어컨, 냉장고,미니바(유료), 드라이기, 욕실용품, 샤워가운, 슬리퍼",
            ),
            cancel = listOf(
                "체크인일 기준 3일전 18시까지 : 100% 환불",
                "체크인일 기준 3일전 18시 이후~당일 및 No-show : 환불  불가 (연박의 경우 최초 1박 환불 불가, 이후 일자 100% 환불)",
                "취소, 환불 시 수수료가 발생할 수 있습니다.",
                "아래 객실은 별도의 취소 규정이 적용되오니 참고 부탁드립니다",
                "[룸온리 특가], [호텔딜], [오픈런] 객실: 체크인일 기준 7일 전 18시까지 100% 환불, 이후 환불 불가 (연박의 경우 최초 1회 환불 불 이 일자 100% 환불)",
                "[특가],[취소불가] 객실: 취소, 변경, 환불 불가",
                "예약 후 10분 내 취소될 경우 취소 수수료가 발생하지 않습니다. (*체크인 시간 경과 후 제외)",
                "예약 후 10분 경과 시엔 해당 숙소의 취소 및 환불 규정이 적용됩니다.",
            )
        )
    }

    fun setRoomData(
        room: Room
    ) {
        _room.value = room
    }

    data class Detail(
        var information: List<String>,
        var facilities: List<String>,
        var cancel: List<String>,
    )

}