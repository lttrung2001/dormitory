package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.VerifyAdminUseCase
import com.lttrung.dormitory.domain.usecases.impl.VerifyAdminUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface VerifyAdminUseCaseModule {
    @Binds
    fun bindsVerifyAdminUseCase(impl: VerifyAdminUseCaseImpl): VerifyAdminUseCase
}