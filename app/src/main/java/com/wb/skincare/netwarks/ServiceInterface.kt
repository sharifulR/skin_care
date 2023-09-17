package com.wb.skincare.netwarks

import com.wb.skincare.models.ServiceResponse
import com.wb.skincare.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServiceInterface {

    @Headers("Content-Type: application/json")
    @GET(Constants.SERVICE_ENDPOINT)
    suspend fun getService(@Header("Authorization") token:String, @Query("Page") page: Int): Response<ServiceResponse>

     @Headers("Content-Type: application/json")
    @GET(Constants.SERVICE_CATEGORY_ENDPOINT)
    suspend fun getServiceCategory(@Header("Authorization") token:String, @Query("Page") page: Int): Response<ServiceResponse>


}