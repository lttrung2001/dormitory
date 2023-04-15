package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ContractService {
    @GET("api/student/contract/")
    fun fetchRoomContract(): Single<Response<FetchRoomContractResponse>>

    @GET("api/student/contract/create/{roomId}")
    fun registerRoom(@Path("roomId") roomId: Int): Single<Response<Boolean>>

    @GET("api/student/contract/cancel")
    fun cancelContract(): Single<Response<String>>

    @GET("api/student/contract/extend")
    fun extendRoom(): Single<Response<Boolean>>
}