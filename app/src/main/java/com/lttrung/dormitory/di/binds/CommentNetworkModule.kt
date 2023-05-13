package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.data.network.CommentNetwork
import com.lttrung.dormitory.domain.data.network.impl.CommentNetworkImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommentNetworkModule {
    @Binds
    fun bindsCommentNetwork(impl: CommentNetworkImpl): CommentNetwork
}