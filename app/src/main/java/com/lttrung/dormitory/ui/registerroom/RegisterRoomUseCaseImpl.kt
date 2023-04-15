package com.lttrung.dormitory.ui.registerroom

import com.lttrung.dormitory.database.repositories.ContractRepositories
import com.lttrung.dormitory.database.repositories.UserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RegisterRoomUseCaseImpl @Inject constructor(
    private val contractRepositories: ContractRepositories
) : RegisterRoomUseCase {
    override fun registerRoom(roomId: Int): Single<Boolean> {
        return contractRepositories.registerRoom(roomId)
    }
}