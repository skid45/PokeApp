package com.skid.pokeapp.di

import android.content.Context
import com.skid.core.app.ApplicationProvider
import com.skid.core.di.CoreComponent
import com.skid.core.di.CoreProvider
import com.skid.core.network.NetworkProvider
import com.skid.network.di.NetworkComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        NetworkProvider::class,
        CoreProvider::class,
    ],
    modules = [
        ScreenEntriesModule::class,
    ]
)
interface ApplicationComponent : ApplicationProvider {

    companion object {

        fun init(
            context: Context,
        ): ApplicationProvider {
            return DaggerApplicationComponent.factory()
                .create(
                    networkProvider = NetworkComponent.init(),
                    coreProvider = CoreComponent.init(context)
                )
        }
    }

    @Component.Factory
    interface Factory {

        fun create(
            networkProvider: NetworkProvider,
            coreProvider: CoreProvider,
        ): ApplicationProvider
    }
}