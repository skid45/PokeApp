package com.skid.core.app

import androidx.compose.runtime.compositionLocalOf
import com.skid.core.di.CoreProvider
import com.skid.core.navigation.di.ScreenEntriesMapProvider
import com.skid.core.network.NetworkProvider

interface ApplicationProvider :
    NetworkProvider,
    CoreProvider,
    ScreenEntriesMapProvider

val LocalApplicationProvider = compositionLocalOf<ApplicationProvider> {
    error("No app provider found!")
}