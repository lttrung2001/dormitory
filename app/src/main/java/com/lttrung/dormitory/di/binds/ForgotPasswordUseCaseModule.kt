package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.ui.forgotpassword.ForgotPasswordUseCase
import com.lttrung.dormitory.ui.forgotpassword.ForgotPasswordUseCaseImpl
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