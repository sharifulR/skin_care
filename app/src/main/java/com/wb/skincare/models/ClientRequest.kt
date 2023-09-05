package com.wb.skincare.models


import com.google.gson.annotations.SerializedName

data class ClientRequest(
    @SerializedName("client_address")
    val clientAddress: String,
    @SerializedName("client_email")
    val clientEmail: String,
    @SerializedName("client_mobile")
    val clientMobile: String,
    @SerializedName("client_name")
    val clientName: String
)