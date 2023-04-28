package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.repositories.RoomManagementRepositories
import com.lttrung.dormitory.domain.repositories.impl.RoomManagementRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RoomManagementRepositoriesModule {
    @Binds
    fun bindsRoomManagementRepositories(impl: RoomManagementRepositoriesImpl): RoomManagementRepositories
}