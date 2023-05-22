package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.data.network.AdminUserNetwork
import com.lttrung.dormitory.domain.data.network.impl.AdminUserRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AdminUserNetworkModule {
    @Binds
    fun bindsAdminUserNetwork(impl: AdminUserRetrofitImpl): AdminUserNetwork
}