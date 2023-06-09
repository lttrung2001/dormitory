package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.repositories.RoomRepositories
import com.lttrung.dormitory.domain.repositories.impl.RoomRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RoomRepositoriesModule {
    @Binds
    fun bindsRoomRepositories(impl: RoomRepositoriesImpl): RoomRepositories
}