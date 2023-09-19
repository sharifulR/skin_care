package com.wb.skincare.models


import com.google.gson.annotations.SerializedName

data class ProviderRequest(
    @SerializedName("provider_name")
    val providerName: String
)