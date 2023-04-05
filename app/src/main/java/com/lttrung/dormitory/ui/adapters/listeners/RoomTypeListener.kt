package com.lttrung.dormitory.ui.adapters.listeners

import com.lttrung.dormitory.database.data.network.models.RoomType

interface RoomTypeListener {
    fun onClick(roomType: RoomType)
}