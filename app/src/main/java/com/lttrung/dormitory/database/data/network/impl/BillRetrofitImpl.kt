package com.lttrung.dormitory.database.data.network.impl

import com.lttrung.dormitory.database.data.network.BillNetwork
import com.lttrung.dormitory.database.data.network.models.ElectricBill
import com.lttrung.dormitory.database.data.network.models.WaterBill
import com.lttrung.dormitory.database.data.network.services.BillService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Inject

class BillRetrofitImpl @Inject constructor(
    private val service: BillService
) : BillNetwork {
    override fun fetchRoomBills(): Single<Type> {
        TODO("Not yet implemented")
    }

    override fun fetchElectricBills(): Single<List<ElectricBill>> {
        return service.fetchElectricBills().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> { response.body()!! }
                else -> { throw FailedException() }
            }
        }
    }

    override fun fetchWaterBills(): Single<List<WaterBill>> {
        return service.fetchWaterBills().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> { response.body()!! }
                else -> { throw FailedException() }
            }
        }
    }
}