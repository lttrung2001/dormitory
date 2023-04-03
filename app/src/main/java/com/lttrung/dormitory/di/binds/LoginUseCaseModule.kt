package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.ui.login.LoginUseCase
import com.lttrung.dormitory.ui.login.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface LoginUseCaseModule {
    @Binds
    fun bindsLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase
}