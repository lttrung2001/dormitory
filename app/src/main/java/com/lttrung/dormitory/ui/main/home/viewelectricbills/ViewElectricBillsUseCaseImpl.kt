package com.lttrung.dormitory.ui.main.home.viewelectricbills

import com.lttrung.dormitory.database.data.network.models.ElectricBill
import com.lttrung.dormitory.database.repositories.BillRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewElectricBillsUseCaseImpl @Inject constructor(
    private val repositories: BillRepositories
) : ViewElectricBillsUseCase {
    override fun getElectricBills(): Single<List<ElectricBill>> {
        return repositories.fetchElectricBills()
    }
}