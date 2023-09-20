package com.wb.skincare.models.provider


import com.google.gson.annotations.SerializedName

data class ProviderRequest(
    @SerializedName("provider_name")
    val providerName: String
)