package com.wb.skincare.models.client


import com.google.gson.annotations.SerializedName

data class updateClientResponse(
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
        @SerializedName("client_image")
        val clientImage: Any,
        @SerializedName("client_mobile")
        val clientMobile: String,
        @SerializedName("client_name")
        val clientName: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}