package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.GetCommentsUseCase
import com.lttrung.dormitory.domain.usecases.impl.GetCommentsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GetCommentsUseCaseModule {
    @Binds
    fun bindsGetCommentsUseCase(impl: GetCommentsUseCaseImpl): GetCommentsUseCase
}