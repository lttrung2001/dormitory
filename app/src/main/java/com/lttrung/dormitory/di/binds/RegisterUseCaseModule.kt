package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.ui.register.RegisterUseCase
import com.lttrung.dormitory.ui.register.RegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RegisterUseCaseModule {
    @Binds
    fun bindsRegisterUseCase(impl: RegisterUseCaseImpl): RegisterUseCase
}