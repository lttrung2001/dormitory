package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.WaterBill
import com.lttrung.dormitory.domain.repositories.BillRepositories
import com.lttrung.dormitory.domain.usecases.ViewWaterBillsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewWaterBillsUseCaseImpl @Inject constructor(
    private val repositories: BillRepositories
) : ViewWaterBillsUseCase {
    override fun execute(): Single<List<WaterBill>> {
        return repositories.fetchWaterBills()
    }
}