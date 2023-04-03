package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.repositories.login.LoginRepositories
import com.lttrung.dormitory.database.repositories.login.LoginRepositoriesImpl
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