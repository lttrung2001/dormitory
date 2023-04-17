package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.domain.repositories.ContractRepositories
import com.lttrung.dormitory.domain.usecases.GetRoomContractUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRoomContractUseCaseImpl @Inject constructor(
    private val contractRepositories: ContractRepositories
) : GetRoomContractUseCase {
    override fun execute(): Single<FetchRoomContractResponse> {
        return contractRepositories.getRoomContract()
    }
}