package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.database.repositories.RoomTypeRepositories
import com.lttrung.dormitory.database.repositories.impl.RoomTypeRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RoomTypeRepositoriesModule {
    @Binds
    fun bindsRoomTypeRepositories(impl: RoomTypeRepositoriesImpl): RoomTypeRepositories
}