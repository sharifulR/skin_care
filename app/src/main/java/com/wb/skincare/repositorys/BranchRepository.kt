package com.wb.skincare.repositorys

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wb.skincare.models.branch.AddBranchResponse
import com.wb.skincare.models.branch.BranchRequest
import com.wb.skincare.models.branch.BranchResponse
import com.wb.skincare.netwarks.BranchInterface
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.Constants
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class BranchRepository @Inject constructor(private val branchInterface: BranchInterface){
    private val _createBranchLiveData= MutableLiveData<NetworkResult<AddBranchResponse>>()
    val createBranchLiveData: LiveData<NetworkResult<AddBranchResponse>>
        get() = _createBranchLiveData

    private val _branchLiveData= MutableLiveData<NetworkResult<BranchResponse>>()
    val branchLiveData: LiveData<NetworkResult<BranchResponse>>
        get()=_branchLiveData

    suspend fun createBranch(branchRequest: BranchRequest){
        //val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiZTkxZjEwYzA4ZTdiN2JmOTVlOWFkZmZjZDlhMDNkMTkyYzAwNzk1N2Y5Mzc0YWFlOTJkMzhkZTAyNmZjMGJkNDViYTdlZGIyOTJjOTkwNmUiLCJpYXQiOjE2OTM4NDc3NjQuNTI0MjQ3ODg0NzUwMzY2MjEwOTM3NSwibmJmIjoxNjkzODQ3NzY0LjUyNDI0OTA3Njg0MzI2MTcxODc1LCJleHAiOjE3MjU0NzAxNjQuNTE2NDk0MDM1NzIwODI1MTk1MzEyNSwic3ViIjoiMiIsInNjb3BlcyI6W119.MyEl_JT13ce9uo1GpJ-jtaUnQJgjoSqV9A3yMwDlTD3Kng1DgjWInrMoikPpiUpSxysqQBAiGhvu6a0Gr0e5j3z70sD0bC7K8u8fZ-DdO6Q1_Rq15IKX1JYWdOeIsfn5REaX7xiv4eM75RX1JAiUOC3AfeOxnM1ugH6wt4wZJa-2XC-An4eweb6N4NOl7qOn9YiKq__VF_hp8f52WXGY9a5nqdTOcfsu1336zzZlLhiWiAJqPr68Ol9oEzKTu8qDLi1U7sugo8-hRHLvQqrlQcwd_gSW1l1mzH246rf8c_Mvrz0hborsgJZJQyopHZbOK835RBPGaVcqMKJaCA_ZJWBV75KVRB_zvN5FSRSnWKqTxXGAWDHuwGasOpr7eIyl857fN9EI0pDHkL6-ecwbuZbg-JBmm8uQdMZ0r38xjRr1eY_BJursTihMOnpTim7xNRcCrVNngqsSD-nryUJm4izfjqGs_RGrGqqOWFJ6jm3CWNpSOHQMGAOt1rdJDOF6exu76VvQaeUJtXC2oVzzxuQzK9FxhzB7BavLD10BJZNhfgnICJAzhZpq3BxlhqPla00AD5k7L98SnH9Z58VxSSHCgeZno_2A8zQf-DAX9GidAEM4pRX2uMGvNRhXUju2a_kKmSa9rqLyqvi61Oy8K457_NIYCHvssLBdOT3_Qa0"
        _createBranchLiveData.postValue(NetworkResult.Loading())
        val response=branchInterface.createBranch(Constants.USER_TOKEN,branchRequest)
        handleResponse(response)
        Log.d("ClientRepository", "CreatedClient: ${response.body()}")
        Log.d("ClientRepository", "CreatedClient: ${response.code()}")
    }

    suspend fun getBranch(){
        //val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiZTkxZjEwYzA4ZTdiN2JmOTVlOWFkZmZjZDlhMDNkMTkyYzAwNzk1N2Y5Mzc0YWFlOTJkMzhkZTAyNmZjMGJkNDViYTdlZGIyOTJjOTkwNmUiLCJpYXQiOjE2OTM4NDc3NjQuNTI0MjQ3ODg0NzUwMzY2MjEwOTM3NSwibmJmIjoxNjkzODQ3NzY0LjUyNDI0OTA3Njg0MzI2MTcxODc1LCJleHAiOjE3MjU0NzAxNjQuNTE2NDk0MDM1NzIwODI1MTk1MzEyNSwic3ViIjoiMiIsInNjb3BlcyI6W119.MyEl_JT13ce9uo1GpJ-jtaUnQJgjoSqV9A3yMwDlTD3Kng1DgjWInrMoikPpiUpSxysqQBAiGhvu6a0Gr0e5j3z70sD0bC7K8u8fZ-DdO6Q1_Rq15IKX1JYWdOeIsfn5REaX7xiv4eM75RX1JAiUOC3AfeOxnM1ugH6wt4wZJa-2XC-An4eweb6N4NOl7qOn9YiKq__VF_hp8f52WXGY9a5nqdTOcfsu1336zzZlLhiWiAJqPr68Ol9oEzKTu8qDLi1U7sugo8-hRHLvQqrlQcwd_gSW1l1mzH246rf8c_Mvrz0hborsgJZJQyopHZbOK835RBPGaVcqMKJaCA_ZJWBV75KVRB_zvN5FSRSnWKqTxXGAWDHuwGasOpr7eIyl857fN9EI0pDHkL6-ecwbuZbg-JBmm8uQdMZ0r38xjRr1eY_BJursTihMOnpTim7xNRcCrVNngqsSD-nryUJm4izfjqGs_RGrGqqOWFJ6jm3CWNpSOHQMGAOt1rdJDOF6exu76VvQaeUJtXC2oVzzxuQzK9FxhzB7BavLD10BJZNhfgnICJAzhZpq3BxlhqPla00AD5k7L98SnH9Z58VxSSHCgeZno_2A8zQf-DAX9GidAEM4pRX2uMGvNRhXUju2a_kKmSa9rqLyqvi61Oy8K457_NIYCHvssLBdOT3_Qa0"
        _branchLiveData.postValue(NetworkResult.Loading())
        val response=branchInterface.getBranch(Constants.USER_TOKEN, 1)

        if (response.isSuccessful && response.body() != null) {
            _branchLiveData.postValue(NetworkResult.Success(response.body()!!))

            Log.d(Constraints.TAG, "login: ${response.body()}")
            Log.d("TAG", "getAllClass: ${response.body()}")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _branchLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

            Log.d(Constraints.TAG, "login: ${errorObj}")
        } else {
            _branchLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }

    private fun handleResponse(response: Response<AddBranchResponse>){
        if (response.isSuccessful && response.body() != null){
            _createBranchLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else{
            _createBranchLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}