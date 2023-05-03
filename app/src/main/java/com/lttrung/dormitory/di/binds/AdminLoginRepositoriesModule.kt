package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.repositories.AdminLoginRepositories
import com.lttrung.dormitory.domain.repositories.impl.AdminLoginRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AdminLoginRepositoriesModule {
    @Binds
    fun bindsAdminLoginRepositories(impl: AdminLoginRepositoriesImpl): AdminLoginRepositories
}