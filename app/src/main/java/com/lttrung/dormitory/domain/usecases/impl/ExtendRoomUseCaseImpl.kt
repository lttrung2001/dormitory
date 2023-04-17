package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.ContractRepositories
import com.lttrung.dormitory.domain.usecases.ExtendRoomUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ExtendRoomUseCaseImpl @Inject constructor(
    private val contractRepositories: ContractRepositories
) : ExtendRoomUseCase {
    override fun execute(): Single<Unit> {
        return contractRepositories.extendRoom()
    }
}