package com.wb.skincare.models


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("userData")
    val userData: UserData
) {
    data class UserData(
        @SerializedName("address")
        val address: String,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("device_token")
        val deviceToken: Any,
        @SerializedName("email")
        val email: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("mobile")
        val mobile: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("package_status")
        val packageStatus: Int,
        @SerializedName("role")
        val role: String,
        @SerializedName("status")
        val status: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("verify_code")
        val verifyCode: String,
        @SerializedName("verify_expires_at")
        val verifyExpiresAt: String
    )
}