package com.lttrung.dormitory.ui.main.home.viewwaterbills

import com.lttrung.dormitory.database.data.network.models.WaterBill
import com.lttrung.dormitory.database.repositories.BillRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewWaterBillsUseCaseImpl @Inject constructor(
    private val repositories: BillRepositories
) : ViewWaterBillsUseCase {
    override fun getWaterBills(): Single<List<WaterBill>> {
        return repositories.fetchWaterBills()
    }
}