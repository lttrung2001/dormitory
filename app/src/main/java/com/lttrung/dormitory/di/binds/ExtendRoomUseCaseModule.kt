package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.ExtendRoomUseCase
import com.lttrung.dormitory.domain.usecases.impl.ExtendRoomUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ExtendRoomUseCaseModule {
    @Binds
    fun bindsExtendRoomUseCase(impl: ExtendRoomUseCaseImpl): ExtendRoomUseCase
}