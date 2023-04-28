package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.ui.roommanagement.RoomsManagementUseCase
import com.lttrung.dormitory.ui.roommanagement.RoomsManagementUseCaseImpl
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