package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.data.network.ContractNetwork
import com.lttrung.dormitory.database.data.network.impl.ContractRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ContractNetworkModule {
    @Binds
    fun bindsContractNetwork(impl: ContractRetrofitImpl): ContractNetwork
}