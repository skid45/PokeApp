package com.skid.core.navigation.di

import com.skid.core.navigation.ScreenEntry
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ScreenEntryKey(val value: KClass<out ScreenEntry>)