package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.data.network.AdminLoginNetwork
import com.lttrung.dormitory.domain.data.network.impl.AdminLoginRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AdminLoginNetworkModule {
    @Binds
    fun bindsAdminLoginNetwork(impl: AdminLoginRetrofitImpl): AdminLoginNetwork
}