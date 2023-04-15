package com.lttrung.dormitory.database.data.network.impl

import com.lttrung.dormitory.database.data.network.ContractNetwork
import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.database.data.network.services.ContractService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ContractRetrofitImpl @Inject constructor(
    private val service: ContractService
) : ContractNetwork {
    override fun getRoomContract(): Single<FetchRoomContractResponse> {
        return service.fetchRoomContract().map { response ->
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw FailedException("Get room contract failed.")
            }
        }
    }

    override fun registerRoom(roomId: Int): Single<Boolean> {
        return service.registerRoom(roomId).map { response ->
            if (response.isSuccessful) {
                val isSuccess = response.body()!!
                if (isSuccess) {
                    true
                } else {
                    throw FailedException("Register room failed.")
                }
            } else {
                throw FailedException("Register room failed.")
            }
        }
    }

    override fun cancelContract(): Single<String> {
        return service.cancelContract().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> { response.body()!! }
                else -> { throw FailedException(response.body()!!) }
            }
        }
    }

    override fun extendRoom(): Single<Boolean> {
        return service.extendRoom().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException(response.body()!!.toString())
            }
        }
    }
}