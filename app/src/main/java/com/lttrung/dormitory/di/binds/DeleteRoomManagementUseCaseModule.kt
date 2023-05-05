package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.DeleteRoomManagementUseCase
import com.lttrung.dormitory.domain.usecases.impl.DeleteRoomManagementUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DeleteRoomManagementUseCaseModule {
    @Binds
    fun bindsDeleteRoomManagementUseCase(impl: DeleteRoomManagementUseCaseImpl): DeleteRoomManagementUseCase
}