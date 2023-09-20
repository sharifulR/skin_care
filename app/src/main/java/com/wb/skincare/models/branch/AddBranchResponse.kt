package com.wb.skincare.models.branch


import com.google.gson.annotations.SerializedName

data class AddBranchResponse(
    @SerializedName("branchData")
    val branchData: BranchData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class BranchData(
        @SerializedName("branch_address")
        val branchAddress: String,
        @SerializedName("branch_title")
        val branchTitle: String
    )
}