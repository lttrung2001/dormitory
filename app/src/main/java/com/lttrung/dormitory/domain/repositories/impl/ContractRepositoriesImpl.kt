package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.local.UserLocal
import com.lttrung.dormitory.domain.data.network.ContractNetwork
import com.lttrung.dormitory.domain.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.domain.repositories.ContractRepositories
import com.lttrung.dormitory.exceptions.FailedException
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ContractRepositoriesImpl @Inject constructor(
    override val network: ContractNetwork,
    override val local: UserLocal
) : ContractRepositories {
    override fun getRoomContract(): Single<FetchRoomContractResponse> {
        return try {
            network.getRoomContract()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun registerRoom(roomId: Int): Single<Boolean> {
        return try {
            network.registerRoom(roomId)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun cancelContract(): Single<Unit> {
        return try {
            network.cancelContract()
        } catch (ex: FailedException) {
            throw ex
        }
    }

    override fun extendRoom(): Single<Unit> {
        return try {
            network.extendRoom()
        } catch (ex: Exception) {
            throw ex
        }
    }
}