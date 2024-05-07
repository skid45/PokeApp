package com.skid.core.navigation.di

import com.skid.core.navigation.ScreenEntry

typealias ScreenEntriesMap = Map<Class<out ScreenEntry>, @JvmSuppressWildcards ScreenEntry>

interface ScreenEntriesMapProvider {

    val screenEntriesMap: ScreenEntriesMap
}

inline fun <reified T : ScreenEntry> ScreenEntriesMap.getScreenEntry(): T {
    return this[T::class.java] as? T
        ?: error("No screen ${T::class.java.simpleName} found!")
}