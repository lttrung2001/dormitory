package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.WaterBill
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ViewWaterBillsUseCase {
    fun execute(): Single<List<WaterBill>>
}