package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.ChangePasswordUseCase
import com.lttrung.dormitory.domain.usecases.impl.ChangePasswordUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ChangePasswordUseCaseModule {
    @Binds
    fun bindsChangePasswordUseCase(impl: ChangePasswordUseCaseImpl): ChangePasswordUseCase
}