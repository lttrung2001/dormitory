package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.R
import com.lttrung.dormitory.domain.data.local.models.FilterSortModel
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.repositories.RoomRepositories
import com.lttrung.dormitory.domain.usecases.ViewRoomsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewRoomsUseCaseImpl @Inject constructor(
    private val roomRepositories: RoomRepositories
) : ViewRoomsUseCase {
    override fun execute(roomTypeId: Int, filterSortModel: FilterSortModel?): Single<List<Room>> {
        var listRoom = roomRepositories.fetchRooms(roomTypeId)
        filterSortModel?.let {
            listRoom = listRoom.map { rooms ->
                val bedRange = filterSortModel.bedsRange
                rooms.filter {r ->
                    r.availableBeds in bedRange[0] .. bedRange[1]
                }
            }
            listRoom = when(filterSortModel.sortPosition) {
                0 -> { listRoom }
                R.id.button1 -> { listRoom.map { it.sortedBy { it.price } } }
                R.id.button2 -> { listRoom.map { it.sortedByDescending { it.price } } }
                R.id.button3 -> { listRoom.map { it.sortedBy { it.availableBeds } } }
                R.id.button4 -> { listRoom.map { it.sortedByDescending { it.availableBeds } } }
                else -> { listRoom }
            }
        }
        return listRoom
    }
}