package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.data.network.RoomNetwork
import com.lttrung.dormitory.database.data.network.impl.RoomRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RoomNetworkModule {
    @Binds
    fun bindsRoomNetwork(impl: RoomRetrofitImpl): RoomNetwork
}