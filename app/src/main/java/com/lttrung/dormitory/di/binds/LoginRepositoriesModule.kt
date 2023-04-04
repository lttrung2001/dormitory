package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.repositories.LoginRepositories
import com.lttrung.dormitory.database.repositories.impl.LoginRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginRepositoriesModule {
    @Binds
    fun providesLoginRepositories(impl: LoginRepositoriesImpl): LoginRepositories
}