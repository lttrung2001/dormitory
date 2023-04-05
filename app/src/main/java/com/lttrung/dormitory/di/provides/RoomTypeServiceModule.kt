package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.database.data.network.services.RoomTypeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomTypeServiceModule {
    @Provides
    @Singleton
    fun providesRoomTypeService(@Named("NoTokenRetrofit") retrofit: Retrofit): RoomTypeService {
        return retrofit.create(RoomTypeService::class.java)
    }
}