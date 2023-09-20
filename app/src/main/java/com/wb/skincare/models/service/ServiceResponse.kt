package com.wb.skincare.models.service


import com.google.gson.annotations.SerializedName

data class ServiceResponse(
    @SerializedName("activeServiceCount")
    val activeServiceCount: Int,
    @SerializedName("allServiceCount")
    val allServiceCount: Int,
    @SerializedName("inactiveServiceCount")
    val inactiveServiceCount: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("serviceCategoryData")
    val serviceCategoryData: List<ServiceCategoryData>,
    @SerializedName("serviceData")
    val serviceData: ServiceData,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class ServiceCategoryData(
        @SerializedName("category_name")
        val categoryName: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("status")
        val status: Int,
        @SerializedName("updated_at")
        val updatedAt: String
    )

    data class ServiceData(
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
            @SerializedName("category_id")
            val categoryId: Int,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("service_image")
            val serviceImage: Any,
            @SerializedName("service_name")
            val serviceName: String,
            @SerializedName("service_price")
            val servicePrice: Int,
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