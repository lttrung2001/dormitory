package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.domain.data.network.services.AdminLoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdminLoginServiceModule {
    @Provides
    @Singleton
    fun providesAdminLoginService(@Named("NoTokenRetrofit") retrofit: Retrofit): AdminLoginService {
        return retrofit.create(AdminLoginService::class.java)
    }
}