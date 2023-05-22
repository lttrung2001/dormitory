package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.repositories.AdminUserRepositories
import com.lttrung.dormitory.domain.repositories.impl.AdminUserRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AdminUserRepositoriesModule {
    @Binds
    fun bindsAdminUserRepositories(impl: AdminUserRepositoriesImpl): AdminUserRepositories
}