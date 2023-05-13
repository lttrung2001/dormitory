package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.WriteCommentUseCase
import com.lttrung.dormitory.domain.usecases.impl.WriteCommentUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface WriteCommentUseCaseModule {
    @Binds
    fun bindsWriteCommentUseCase(impl: WriteCommentUseCaseImpl): WriteCommentUseCase
}