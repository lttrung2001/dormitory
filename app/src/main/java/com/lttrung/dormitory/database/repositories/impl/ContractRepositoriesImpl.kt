package com.lttrung.dormitory.database.repositories.impl

import com.lttrung.dormitory.database.data.local.UserLocal
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUserProfile
import com.lttrung.dormitory.database.data.network.ContractNetwork
import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.database.repositories.ContractRepositories
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

    override fun cancelContract(): Single<String> {
        return try {
            network.cancelContract()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun extendRoom(): Single<Boolean> {
        return try {
            network.extendRoom()
        } catch (ex: Exception) {
            throw ex
        }
    }
}