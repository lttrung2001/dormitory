package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.UpdateRoomManagementUseCase
import com.lttrung.dormitory.domain.usecases.impl.UpdateRoomManagementUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UpdateRoomManagementUseCaseModule {
    @Binds
    fun bindsUpdateRoomManagementUseCase(impl: UpdateRoomManagementUseCaseImpl): UpdateRoomManagementUseCase
}