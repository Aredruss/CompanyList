package com.aredruss.companylist.domain

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val image: String,
    @SerializedName("description")
    val desc: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("www")
    val website: String?,
    @SerializedName("phone")
    val phone: String?
)