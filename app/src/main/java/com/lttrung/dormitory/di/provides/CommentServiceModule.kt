package com.lttrung.dormitory.di.provides

import com.lttrung.dormitory.domain.data.network.services.CommentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommentServiceModule {
    @Provides
    @Singleton
    fun providesCommentService(@Named("TokenRetrofit") retrofit: Retrofit): CommentService {
        return retrofit.create(CommentService::class.java)
    }
}