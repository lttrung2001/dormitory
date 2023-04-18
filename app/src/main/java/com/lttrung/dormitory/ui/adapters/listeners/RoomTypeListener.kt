package com.lttrung.dormitory.ui.adapters.listeners

import android.view.View
import com.lttrung.dormitory.databinding.LayoutRoomTypeBinding
import com.lttrung.dormitory.domain.data.network.models.RoomType

interface RoomTypeListener {
    fun onClick(viewBinding: LayoutRoomTypeBinding, roomType: RoomType)
}