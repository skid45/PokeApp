package com.skid.core.resources

import androidx.annotation.StringRes

interface AppResources {

    fun getString(@StringRes id: Int): String

    fun getString(@StringRes id: Int, vararg args: String): String
}