package com.wb.skincare.netwarks

import com.wb.skincare.models.ClientRequest
import com.wb.skincare.models.ClientResponse
import com.wb.skincare.models.updateClientResponse
import com.wb.skincare.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientInterface {

    @Headers("Content-Type: application/json")
    @GET(Constants.CLIENT_ENDPOINT)
    suspend fun getClient(@Header("Authorization") token:String, @Query("Page") page: Int):Response<ClientResponse>

    @POST(Constants.CLIENT_ENDPOINT)
    suspend fun createClient(@Body clientRequest: ClientRequest):Response<ClientResponse>
    @FormUrlEncoded
    @PUT(Constants.CLIENT_UPDATE_ENDPOINT)
    suspend fun updateClient(@Path("clientId") clientId: Int?, clientRequest: ClientRequest):Response<updateClientResponse>
}