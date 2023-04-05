package com.lttrung.dormitory.database.repositories.impl

import com.lttrung.dormitory.database.data.network.BillNetwork
import com.lttrung.dormitory.database.data.network.models.ElectricBill
import com.lttrung.dormitory.database.data.network.models.WaterBill
import com.lttrung.dormitory.database.repositories.BillRepositories
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Inject

class BillRepositoriesImpl @Inject constructor(
    override val network: BillNetwork
) : BillRepositories {
    override fun fetchRoomBills(): Single<Type> {
        TODO("Not yet implemented")
    }

    override fun fetchElectricBills(): Single<List<ElectricBill>> {
        return network.fetchElectricBills()
    }

    override fun fetchWaterBills(): Single<List<WaterBill>> {
        return network.fetchWaterBills()
    }

}