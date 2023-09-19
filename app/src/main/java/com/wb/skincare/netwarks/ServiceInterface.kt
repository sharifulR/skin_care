package com.wb.skincare.netwarks

import com.wb.skincare.models.CategoryWiseService
import com.wb.skincare.models.ServiceCategoryResponse
import com.wb.skincare.models.ServiceResponse
import com.wb.skincare.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface ServiceInterface {

    @Headers("Content-Type: application/json")
    @GET(Constants.SERVICE_ENDPOINT)
    suspend fun getService(@Header("Authorization") token:String, @Query("Page") page: Int): Response<ServiceResponse>

     @Headers("Content-Type: application/json")
    @GET(Constants.SERVICE_CATEGORY_ENDPOINT)
    suspend fun getServiceCategory(@Header("Authorization") token:String, @Query("Page") page: Int): Response<ServiceCategoryResponse>

@Headers("Content-Type: application/json")
    @GET(Constants.CATEGORY_WISE_SERVICE_ENDPOINT)
    suspend fun getCategoryWiseService(@Header("Authorization") token:String, @Path("id") categoryId : Int): Response<CategoryWiseService>


}