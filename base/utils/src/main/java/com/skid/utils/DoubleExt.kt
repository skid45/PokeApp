package com.skid.utils

fun Double.toString(removeTheZeroFractionalPart: Boolean = false): String {
    return if (removeTheZeroFractionalPart && this % 1 == 0.0) {
        this.toInt()
    } else {
        this
    }.toString()
}