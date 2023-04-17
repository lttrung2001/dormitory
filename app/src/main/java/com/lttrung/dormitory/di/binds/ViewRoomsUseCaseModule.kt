package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.domain.usecases.ViewRoomsUseCase
import com.lttrung.dormitory.domain.usecases.impl.ViewRoomsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewRoomsUseCaseModule {
    @Binds
    fun bindsViewRoomsUseCase(impl: ViewRoomsUseCaseImpl): ViewRoomsUseCase
}