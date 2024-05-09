package com.skid.utils

import java.util.Locale

val String.Companion.Empty: String
    get() = ""

fun String.capitalize(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}