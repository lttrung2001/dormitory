package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.database.data.network.services.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginServiceModule {
    @Provides
    @Singleton
    fun providesLoginNetwork(@Named("NoTokenRetrofit") retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }
}