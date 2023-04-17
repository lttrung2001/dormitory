package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.domain.data.network.services.ContractService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContractServiceModule {
    @Provides
    @Singleton
    fun providesContractService(@Named("TokenRetrofit") retrofit: Retrofit): ContractService {
        return retrofit.create(ContractService::class.java)
    }
}