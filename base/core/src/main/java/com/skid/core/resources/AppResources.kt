package com.skid.core.resources

import androidx.annotation.StringRes

interface AppResources {

    fun getString(@StringRes id: Int): String
}