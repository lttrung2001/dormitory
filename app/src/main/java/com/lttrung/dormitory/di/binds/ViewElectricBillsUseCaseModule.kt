package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.ui.main.home.viewelectricbills.ViewElectricBillsUseCase
import com.lttrung.dormitory.ui.main.home.viewelectricbills.ViewElectricBillsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewElectricBillsUseCaseModule {
    @Binds
    fun bindsViewElectricBillsUseCase(impl: ViewElectricBillsUseCaseImpl): ViewElectricBillsUseCase
}