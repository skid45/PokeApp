package com.skid.pokeapp.app

import android.app.Application
import com.skid.core.app.App
import com.skid.core.app.ApplicationProvider
import com.skid.pokeapp.di.ApplicationComponent

class PokeApp : Application(), App {

    private val applicationProvider = ApplicationComponent.init(this)

    override fun getApplicationProvider(): ApplicationProvider = applicationProvider
}