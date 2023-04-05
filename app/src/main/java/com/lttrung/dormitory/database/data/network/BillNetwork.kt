package com.lttrung.dormitory.database.data.network

import com.lttrung.dormitory.database.data.network.models.ElectricBill
import com.lttrung.dormitory.database.data.network.models.WaterBill
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Singleton

@Singleton
interface BillNetwork {
    fun fetchRoomBills(): Single<Type>
    fun fetchElectricBills(): Single<List<ElectricBill>>
    fun fetchWaterBills(): Single<List<WaterBill>>
}