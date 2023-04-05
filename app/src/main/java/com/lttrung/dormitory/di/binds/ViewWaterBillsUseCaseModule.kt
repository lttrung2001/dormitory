package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.ui.main.home.viewwaterbills.ViewWaterBillsUseCase
import com.lttrung.dormitory.ui.main.home.viewwaterbills.ViewWaterBillsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewWaterBillsUseCaseModule {
    @Binds
    fun bindsViewWaterBillsUseCase(impl: ViewWaterBillsUseCaseImpl): ViewWaterBillsUseCase
}