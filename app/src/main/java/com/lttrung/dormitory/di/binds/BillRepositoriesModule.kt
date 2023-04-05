package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.repositories.BillRepositories
import com.lttrung.dormitory.database.repositories.impl.BillRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BillRepositoriesModule {
    @Binds
    fun bindsBillRepositories(impl: BillRepositoriesImpl): BillRepositories
}