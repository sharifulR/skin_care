package com.wb.skincare.models.provider


import com.google.gson.annotations.SerializedName

data class UpdateProviderResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("providerData")
    val providerData: ProviderData,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class ProviderData(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_default")
        val isDefault: Int,
        @SerializedName("provider_address")
        val providerAddress: Any,
        @SerializedName("provider_email")
        val providerEmail: Any,
        @SerializedName("provider_image")
        val providerImage: Any,
        @SerializedName("provider_mobile")
        val providerMobile: Any,
        @SerializedName("provider_name")
        val providerName: String,
        @SerializedName("status")
        val status: Int,
        @SerializedName("updated_at")
        val updatedAt: String
    )
}