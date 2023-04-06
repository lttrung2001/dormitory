package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.repositories.UserRepositories
import com.lttrung.dormitory.database.repositories.impl.UserRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserRepositoriesModule {
    @Binds
    fun bindsUserRepositories(impl: UserRepositoriesImpl): UserRepositories
}