package com.skid.core.resources

import android.content.Context
import javax.inject.Inject

class AppResourcesImpl
@Inject constructor(
    private val context: Context,
) : AppResources {

    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getString(id: Int, vararg args: String): String {
        return context.getString(id, args)
    }
}