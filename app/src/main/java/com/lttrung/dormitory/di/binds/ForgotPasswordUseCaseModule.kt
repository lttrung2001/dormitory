package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.ForgotPasswordUseCase
import com.lttrung.dormitory.domain.usecases.impl.ForgotPasswordUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ForgotPasswordUseCaseModule {
    @Binds
    fun bindsForgotPasswordUseCase(impl: ForgotPasswordUseCaseImpl): ForgotPasswordUseCase
}