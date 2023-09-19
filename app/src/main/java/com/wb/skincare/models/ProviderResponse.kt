package com.wb.skincare.models


import com.google.gson.annotations.SerializedName

data class ProviderResponse(
    @SerializedName("activeProviderCount")
    val activeProviderCount: Int,
    @SerializedName("allProviderCount")
    val allProviderCount: Int,
    @SerializedName("inactiveProviderCount")
    val inactiveProviderCount: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("providerData")
    val providerData: ProviderData,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class ProviderData(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("first_page_url")
        val firstPageUrl: String,
        @SerializedName("from")
        val from: Int,
        @SerializedName("last_page")
        val lastPage: Int,
        @SerializedName("last_page_url")
        val lastPageUrl: String,
        @SerializedName("links")
        val links: List<Link>,
        @SerializedName("next_page_url")
        val nextPageUrl: Any,
        @SerializedName("path")
        val path: String,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("prev_page_url")
        val prevPageUrl: Any,
        @SerializedName("to")
        val to: Int,
        @SerializedName("total")
        val total: Int
    ) {
        data class Data(
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

        data class Link(
            @SerializedName("active")
            val active: Boolean,
            @SerializedName("label")
            val label: String,
            @SerializedName("url")
            val url: String
        )
    }
}