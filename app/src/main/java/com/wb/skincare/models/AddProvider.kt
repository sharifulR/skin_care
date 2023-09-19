package com.wb.skincare.models


import com.google.gson.annotations.SerializedName

data class AddProvider(
    @SerializedName("message")
    val message: String,
    @SerializedName("providerData")
    val providerData: ProviderData,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class ProviderData(
        @SerializedName("provider_name")
        val providerName: String
    )
}