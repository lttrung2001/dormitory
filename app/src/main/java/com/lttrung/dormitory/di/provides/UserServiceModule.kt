package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.domain.data.network.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserServiceModule {
    @Provides
    @Singleton
    fun providesUserService(@Named("TokenRetrofit") retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}