package com.lttrung.dormitory.ui.main.home.viewwaterbills

import com.lttrung.dormitory.database.data.network.models.WaterBill
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ViewWaterBillsUseCase {
    fun getWaterBills(): Single<List<WaterBill>>
}