package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.repositories.ContractRepositories
import com.lttrung.dormitory.domain.usecases.CancelContractUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CancelContractUseCaseImpl @Inject constructor(
    private val contractRepositories: ContractRepositories
) : CancelContractUseCase {
    override fun execute(): Single<Unit> {
        return contractRepositories.cancelContract()
    }
}