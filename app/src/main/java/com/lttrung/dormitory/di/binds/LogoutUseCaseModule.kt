package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.LogoutUseCase
import com.lttrung.dormitory.domain.usecases.impl.LogoutUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface LogoutUseCaseModule {
    @Binds
    fun bindsLogoutUseCase(impl: LogoutUseCaseImpl): LogoutUseCase
}