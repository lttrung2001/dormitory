package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.ViewWaterBillsUseCase
import com.lttrung.dormitory.domain.usecases.impl.ViewWaterBillsUseCaseImpl
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