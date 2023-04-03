package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.data.network.login.LoginNetwork
import com.lttrung.dormitory.database.data.network.login.LoginRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginNetworkModule {
    @Binds
    fun bindsLoginNetwork(impl: LoginRetrofitImpl): LoginNetwork
}