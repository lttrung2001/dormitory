package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.data.network.RoomTypeNetwork
import com.lttrung.dormitory.database.data.network.impl.RoomTypeRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RoomTypeNetworkModule {
    @Binds
    fun binds(impl: RoomTypeRetrofitImpl): RoomTypeNetwork
}