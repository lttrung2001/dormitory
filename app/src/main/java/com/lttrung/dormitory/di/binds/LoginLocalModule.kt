package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.data.local.login.LoginLocal
import com.lttrung.dormitory.database.data.local.login.LoginLocalImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LoginLocalModule {
    @Binds
    fun bindsLoginLocal(impl: LoginLocalImpl): LoginLocal
}