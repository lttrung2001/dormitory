package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.ElectricBill
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ViewElectricBillsUseCase {
    fun execute(): Single<List<ElectricBill>>
}