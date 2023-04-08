package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.data.local.UserLocal
import com.lttrung.dormitory.database.data.local.impl.UserLocalImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserLocalModule {
    @Binds
    fun bindsUserLocal(impl: UserLocalImpl): UserLocal
}