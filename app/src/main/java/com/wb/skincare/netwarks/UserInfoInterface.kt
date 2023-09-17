package com.wb.skincare.netwarks

import com.wb.skincare.models.UserInfo
import com.wb.skincare.utils.Constants.USER_INFO_ENDPOINT
import com.wb.skincare.utils.Constants.USER_INFO_UPDATE_ENDPOINT
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface UserInfoInterface {

    @Headers("Content-Type: application/json")
    @GET(USER_INFO_ENDPOINT)
    suspend fun getUserInfo(@Header("Authorization") token: String):Response<UserInfo>

    @FormUrlEncoded
    @POST(USER_INFO_UPDATE_ENDPOINT)
    suspend fun updateUserInfo(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("mobile") mobile:String,
        @Field("email") email:String
    ):Response<UserInfo>


}