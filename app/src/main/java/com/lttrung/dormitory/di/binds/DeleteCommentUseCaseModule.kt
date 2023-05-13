package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.DeleteCommentUseCase
import com.lttrung.dormitory.domain.usecases.impl.DeleteCommentUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DeleteCommentUseCaseModule {
    @Binds
    fun bindsDeleteCommentUseCase(impl: DeleteCommentUseCaseImpl): DeleteCommentUseCase
}