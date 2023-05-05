package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.AddRoomManagementUseCase
import com.lttrung.dormitory.domain.usecases.impl.AddRoomManagementUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AddRoomManagementUseCaseModule {
    @Binds
    fun bindsAddRoomManagementUseCase(impl: AddRoomManagementUseCaseImpl): AddRoomManagementUseCase
}