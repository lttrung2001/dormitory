package com.lttrung.dormitory.database.repositories

import com.lttrung.dormitory.database.data.local.UserLocal
import com.lttrung.dormitory.database.data.network.ContractNetwork
import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ContractRepositories {
    val local: UserLocal
    val network: ContractNetwork
    fun getRoomContract(): Single<FetchRoomContractResponse>
    fun registerRoom(roomId: Int): Single<Boolean>
    fun cancelContract(): Single<String>
    fun extendRoom(): Single<Boolean>
}