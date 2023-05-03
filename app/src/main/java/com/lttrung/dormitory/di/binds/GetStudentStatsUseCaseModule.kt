package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.GetStudentStatsUseCase
import com.lttrung.dormitory.domain.usecases.impl.GetStudentStatsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GetStudentStatsUseCaseModule {
    @Binds
    fun bindsGetStudentStatsUseCase(impl: GetStudentStatsUseCaseImpl): GetStudentStatsUseCase
}