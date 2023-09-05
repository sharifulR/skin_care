package com.wb.skincare.models


import com.google.gson.annotations.SerializedName

data class AddClientResponse(
    @SerializedName("clientData")
    val clientData: ClientData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class ClientData(
        @SerializedName("client_address")
        val clientAddress: String,
        @SerializedName("client_email")
        val clientEmail: String,
        @SerializedName("client_mobile")
        val clientMobile: String,
        @SerializedName("client_name")
        val clientName: String
    )
}