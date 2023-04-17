package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.ViewProfileUseCase
import com.lttrung.dormitory.domain.usecases.impl.ViewProfileUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewProfileUseCaseModule {
    @Binds
    fun bindsViewProfileUseCase(impl: ViewProfileUseCaseImpl): ViewProfileUseCase
}