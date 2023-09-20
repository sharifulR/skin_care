package com.wb.skincare.netwarks

import com.wb.skincare.models.*
import com.wb.skincare.models.branch.AddBranchResponse
import com.wb.skincare.models.branch.BranchRequest
import com.wb.skincare.models.branch.BranchResponse
import com.wb.skincare.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface BranchInterface {

    @Headers("Content-Type: application/json")
    @POST(Constants.BRANCH_ENDPOINT)
    suspend fun createBranch(@Header("Authorization") token:String, @Body branchRequest: BranchRequest): Response<AddBranchResponse>


    @Headers("Content-Type: application/json")
    @GET(Constants.BRANCH_ENDPOINT)
    suspend fun getBranch(@Header("Authorization") token:String, @Query("Page") page: Int): Response<BranchResponse>

    /*@Headers("Content-Type: application/json")
    @PUT(Constants.UPDATE_BRANCH_ENDPOINT)
    suspend fun updateBranch(@Path("id") providerId: Int?, @Body branchRequest: BranchRequest): Response<UpdateProviderResponse>
*/
}