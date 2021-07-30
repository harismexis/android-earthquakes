package com.example.earthquakes.framework.data.network.model

import com.google.gson.annotations.SerializedName

data class RemoteQuake(
    @SerializedName("eqid")
    val id: String?,
    val datetime: String?,
    val depth: Float?,
    @SerializedName("lng")
    val longitude: Float?,
    @SerializedName("lat")
    val latitude: Float?,
    @SerializedName("src")
    val source: String?,
    val magnitude: Float?
)
