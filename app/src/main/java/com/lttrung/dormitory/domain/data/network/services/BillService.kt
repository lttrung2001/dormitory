package com.lttrung.dormitory.domain.data.network.services

import com.lttrung.dormitory.domain.data.network.models.ElectricBill
import com.lttrung.dormitory.domain.data.network.models.WaterBill
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface BillService {
    @GET("api/bills/water")
    fun fetchElectricBills(): Single<Response<List<ElectricBill>>>

    @GET("api/bills/electric")
    fun fetchWaterBills(): Single<Response<List<WaterBill>>>
}