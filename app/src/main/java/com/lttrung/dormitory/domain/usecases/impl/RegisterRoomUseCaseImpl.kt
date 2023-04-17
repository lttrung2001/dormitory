package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.ContractRepositories
import com.lttrung.dormitory.domain.usecases.RegisterRoomUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RegisterRoomUseCaseImpl @Inject constructor(
    private val contractRepositories: ContractRepositories
) : RegisterRoomUseCase {
    override fun execute(roomId: Int): Single<Boolean> {
        return contractRepositories.registerRoom(roomId)
    }
}