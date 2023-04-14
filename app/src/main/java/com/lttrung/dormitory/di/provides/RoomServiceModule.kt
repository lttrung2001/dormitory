package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.database.data.network.services.RoomService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomServiceModule {
    @Provides
    @Singleton
    fun providesRoomService(@Named("TokenRetrofit") retrofit: Retrofit): RoomService {
        return retrofit.create(RoomService::class.java)
    }
}