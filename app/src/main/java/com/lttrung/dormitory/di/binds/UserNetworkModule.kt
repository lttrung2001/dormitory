package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.data.network.UserNetwork
import com.lttrung.dormitory.database.data.network.impl.UserRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserNetworkModule {
    @Binds
    fun bindsUserNetwork(impl: UserRetrofitImpl): UserNetwork
}