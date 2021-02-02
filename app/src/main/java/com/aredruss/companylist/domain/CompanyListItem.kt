package com.aredruss.companylist.domain

import com.google.gson.annotations.SerializedName

data class CompanyListItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String
)
