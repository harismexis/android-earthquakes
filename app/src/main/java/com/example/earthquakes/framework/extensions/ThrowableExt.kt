package com.example.earthquakes.framework.extensions

import retrofit2.HttpException

fun Throwable.getErrorMessage(): String {
    var errorMsg = "$this, null error message"
    this.message?.let {
        errorMsg = it
    }
    return errorMsg
}