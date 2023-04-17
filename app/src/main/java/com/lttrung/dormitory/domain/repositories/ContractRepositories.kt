package com.lttrung.dormitory.domain.repositories

import com.lttrung.dormitory.domain.data.local.UserLocal
import com.lttrung.dormitory.domain.data.network.ContractNetwork
import com.lttrung.dormitory.domain.data.network.models.FetchRoomContractResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ContractRepositories {
    val local: UserLocal
    val network: ContractNetwork
    fun getRoomContract(): Single<FetchRoomContractResponse>
    fun registerRoom(roomId: Int): Single<Boolean>
    fun cancelContract(): Single<Unit>
    fun extendRoom(): Single<Unit>
}