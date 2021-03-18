package com.example.earthquakes.framework.util

import android.content.Intent
import android.net.Uri

fun getGoogleMapsUrlAt(
    lat: Float,
    lon: Float
): Uri {
    return Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat,$lon")
}

fun getMapIntent(
    lat: Float,
    lon: Float
): Intent {
    return Intent(Intent.ACTION_VIEW, getGoogleMapsUrlAt(lat, lon)).apply {
        setPackage("com.google.android.apps.maps")
    }
}