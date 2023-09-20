package com.wb.skincare.netwarks

import com.wb.skincare.models.*
import com.wb.skincare.models.provider.AddProvider
import com.wb.skincare.models.provider.ProviderRequest
import com.wb.skincare.models.provider.ProviderResponse
import com.wb.skincare.models.provider.UpdateProviderResponse
import com.wb.skincare.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface ProviderInterface {

    @Headers("Content-Type: application/json")
    @POST(Constants.PROVIDER_ENDPOINT)
    suspend fun createProvider(@Header("Authorization") token:String,@Body providerRequest: ProviderRequest):Response<AddProvider>


    @Headers("Content-Type: application/json")
    @GET(Constants.PROVIDER_ENDPOINT)
    suspend fun getProvider(@Header("Authorization") token:String, @Query("Page") page: Int): Response<ProviderResponse>

    @Headers("Content-Type: application/json")
    @PUT(Constants.UPDATE_PROVIDER_ENDPOINT)
    suspend fun updateProvider(@Path("id") providerId: Int?,@Body  providerRequest: ProviderRequest):Response<UpdateProviderResponse>

}