package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.GetGenderStatsUseCase
import com.lttrung.dormitory.domain.usecases.impl.GetGenderStatsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GetGenderStatsUseCaseModule {
    @Binds
    fun bindsGetGenderStatsUseCase(impl: GetGenderStatsUseCaseImpl): GetGenderStatsUseCase
}