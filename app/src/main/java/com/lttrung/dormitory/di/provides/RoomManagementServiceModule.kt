package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.domain.data.network.services.RoomManagementService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomManagementServiceModule {
    @Provides
    @Singleton
    fun providesRoomManagementService(@Named("TokenRetrofit") retrofit: Retrofit): RoomManagementService {
        return retrofit.create(RoomManagementService::class.java)
    }
}