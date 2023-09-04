package com.wb.skincare.netwarks

import com.wb.skincare.models.ClientResponse
import com.wb.skincare.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ClientInterface {

    @Headers("Content-Type: application/json")
    @GET(Constants.CLIENT_ENDPOINT)
    suspend fun getClient(@Header("Authorization")token:String, @Query("page")page:Int):Response<ClientResponse>
}