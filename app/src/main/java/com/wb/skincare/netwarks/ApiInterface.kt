package com.wb.wbsoftware.networks

import com.wb.skincare.models.LoginRequest
import com.wb.skincare.models.LoginResponse
import com.wb.skincare.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST(Constants.LOGIN_ENDPOINT)
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>


}