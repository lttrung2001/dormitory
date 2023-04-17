package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.domain.data.network.services.BillService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BillServiceModule {
    @Provides
    @Singleton
    fun providesBillService(@Named("TokenRetrofit") retrofit: Retrofit): BillService {
        return retrofit.create(BillService::class.java)
    }
}