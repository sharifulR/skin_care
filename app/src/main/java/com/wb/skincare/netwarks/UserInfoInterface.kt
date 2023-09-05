package com.wb.skincare.netwarks

import com.wb.skincare.models.UserInfo
import com.wb.skincare.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface UserInfoInterface {

    @Headers("Content-Type: application/json")
    @GET(Constants.USER_INFO_ENDPOINT)
    suspend fun getUserInfo(@Header("Authorization") token: String):Response<UserInfo>


}