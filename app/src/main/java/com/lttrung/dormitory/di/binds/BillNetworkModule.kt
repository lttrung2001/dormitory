package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.data.network.BillNetwork
import com.lttrung.dormitory.domain.data.network.impl.BillRetrofitImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BillNetworkModule {
    @Binds
    fun bindsBillNetwork(impl: BillRetrofitImpl): BillNetwork
}