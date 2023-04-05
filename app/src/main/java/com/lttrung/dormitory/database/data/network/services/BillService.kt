package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.ElectricBill
import com.lttrung.dormitory.database.data.network.models.WaterBill
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface BillService {
    @GET("/get-electric-bills")
    fun fetchElectricBills(): Single<Response<List<ElectricBill>>>

    @GET("/get-water-bills")
    fun fetchWaterBills(): Single<Response<List<WaterBill>>>
}