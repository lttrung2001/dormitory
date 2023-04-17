package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.RegisterUseCase
import com.lttrung.dormitory.domain.usecases.impl.RegisterUseCaseImpl
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