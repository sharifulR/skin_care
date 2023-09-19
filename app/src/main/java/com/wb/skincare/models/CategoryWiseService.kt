package com.wb.skincare.models


import com.google.gson.annotations.SerializedName

data class CategoryWiseService(
    @SerializedName("message")
    val message: String,
    @SerializedName("serviceData")
    val serviceData: List<ServiceData>,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class ServiceData(
        @SerializedName("category_id")
        val categoryId: Int,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("service_category_data")
        val serviceCategoryData: ServiceCategoryData,
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
    }
}