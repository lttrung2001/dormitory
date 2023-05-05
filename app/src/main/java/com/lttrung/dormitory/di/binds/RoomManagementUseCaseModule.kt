package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.RoomsManagementUseCase
import com.lttrung.dormitory.domain.usecases.impl.RoomsManagementUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RoomManagementUseCaseModule {
    @Binds
    fun bindsRoomManagementUseCase(impl: RoomsManagementUseCaseImpl): RoomsManagementUseCase
}