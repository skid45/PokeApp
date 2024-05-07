package com.skid.network.di

import com.skid.core.network.NetworkProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent : NetworkProvider {

    companion object {

        fun init(): NetworkProvider = DaggerNetworkComponent.factory().create()
    }

    @Component.Factory
    interface Factory {

        fun create(): NetworkProvider
    }
}