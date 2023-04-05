package com.lttrung.dormitory.di.binds

import com.lttrung.dormitory.ui.main.home.HomeUseCase
import com.lttrung.dormitory.ui.main.home.HomeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface HomeUseCaseModule {
    @Binds
    fun bindsHomeUseCase(impl: HomeUseCaseImpl): HomeUseCase
}