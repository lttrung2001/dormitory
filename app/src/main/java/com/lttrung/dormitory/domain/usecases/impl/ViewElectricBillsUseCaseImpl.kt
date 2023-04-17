package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.ElectricBill
import com.lttrung.dormitory.domain.repositories.BillRepositories
import com.lttrung.dormitory.domain.usecases.ViewElectricBillsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewElectricBillsUseCaseImpl @Inject constructor(
    private val repositories: BillRepositories
) : ViewElectricBillsUseCase {
    override fun execute(): Single<List<ElectricBill>> {
        return repositories.fetchElectricBills()
    }
}