package com.skid.core.di

import com.skid.core.resources.AppResources
import com.skid.core.resources.AppResourcesImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppResourcesModule {

    @Singleton
    @Binds
    fun bindAppResources(impl: AppResourcesImpl): AppResources
}