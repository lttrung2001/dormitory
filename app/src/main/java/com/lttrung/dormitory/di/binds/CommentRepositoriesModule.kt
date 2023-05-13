package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.repositories.CommentRepositories
import com.lttrung.dormitory.domain.repositories.impl.CommentRepositoriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommentRepositoriesModule {
    @Binds
    fun bindsCommentRepositories(impl: CommentRepositoriesImpl): CommentRepositories
}