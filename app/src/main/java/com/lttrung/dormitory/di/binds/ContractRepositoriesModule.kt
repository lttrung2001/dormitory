package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.repositories.ContractRepositories
import com.lttrung.dormitory.database.repositories.impl.ContractRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ContractRepositoriesModule {
    @Binds
    fun bindsContractRepositories(impl: ContractRepositoriesImpl): ContractRepositories
}