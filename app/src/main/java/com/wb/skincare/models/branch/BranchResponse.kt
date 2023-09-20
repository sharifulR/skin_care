package com.wb.skincare.models.branch


import com.google.gson.annotations.SerializedName

data class BranchResponse(
    @SerializedName("activeBranchCount")
    val activeBranchCount: Int,
    @SerializedName("allBranchCount")
    val allBranchCount: Int,
    @SerializedName("branchData")
    val branchData: BranchData,
    @SerializedName("inactiveBranchCount")
    val inactiveBranchCount: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class BranchData(
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
            @SerializedName("branch_address")
            val branchAddress: String,
            @SerializedName("branch_title")
            val branchTitle: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("invoice_color")
            val invoiceColor: String,
            @SerializedName("invoice_terms")
            val invoiceTerms: String,
            @SerializedName("phone1")
            val phone1: String,
            @SerializedName("phone2")
            val phone2: String,
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