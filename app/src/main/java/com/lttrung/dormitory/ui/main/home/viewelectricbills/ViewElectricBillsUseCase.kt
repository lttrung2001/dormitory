package com.lttrung.dormitory.ui.main.home.viewelectricbills

import com.lttrung.dormitory.database.data.network.models.ElectricBill
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ViewElectricBillsUseCase {
    fun getElectricBills(): Single<List<ElectricBill>>
}