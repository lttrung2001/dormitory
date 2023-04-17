package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.RegisterRoomUseCase
import com.lttrung.dormitory.domain.usecases.impl.RegisterRoomUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RegisterRoomUseCaseModule {
    @Binds
    fun bindsRegisterRoomUseCase(impl: RegisterRoomUseCaseImpl): RegisterRoomUseCase
}