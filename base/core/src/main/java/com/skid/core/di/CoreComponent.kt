package com.skid.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppResourcesModule::class,
    ]
)
interface CoreComponent : CoreProvider {

    companion object {

        fun init(
            context: Context,
        ) = DaggerCoreComponent.factory().create(context)
    }

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context,
        ): CoreProvider
    }
}