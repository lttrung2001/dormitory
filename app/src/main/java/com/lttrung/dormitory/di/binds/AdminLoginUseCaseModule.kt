package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.AdminLoginUseCase
import com.lttrung.dormitory.domain.usecases.impl.AdminLoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AdminLoginUseCaseModule {
    @Binds
    fun bindsAdminLoginUseCase(impl: AdminLoginUseCaseImpl): AdminLoginUseCase
}