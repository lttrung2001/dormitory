package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.GetRoomTypeStatsUseCase
import com.lttrung.dormitory.domain.usecases.impl.GetRoomTypeStatsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GetRoomTypeStatsUseCaseModule {
    @Binds
    fun bindsGetRoomTypeStatsUseCase(impl: GetRoomTypeStatsUseCaseImpl): GetRoomTypeStatsUseCase
}