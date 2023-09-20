package com.wb.skincare.models.branch


import com.google.gson.annotations.SerializedName

data class BranchRequest(
    @SerializedName("branch_address")
    val branchAddress: String,
    @SerializedName("branch_title")
    val branchTitle: String
)