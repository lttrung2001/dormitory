package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.data.network.RoomManagementNetwork
import com.lttrung.dormitory.domain.data.network.impl.RoomManagementRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RoomManagementNetworkModule {
    @Binds
    fun bindsRoomManagementNetwork(impl: RoomManagementRetrofitImpl): RoomManagementNetwork
}