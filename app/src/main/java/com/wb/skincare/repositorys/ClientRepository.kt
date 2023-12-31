package com.wb.skincare.repositorys

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wb.skincare.models.client.AddClientResponse
import com.wb.skincare.models.client.ClientRequest
import com.wb.skincare.models.client.ClientResponse
import com.wb.skincare.models.client.updateClientResponse
import com.wb.skincare.netwarks.ClientInterface
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.Constants
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class ClientRepository @Inject constructor(private val clientInterface: ClientInterface){
    private val _clientLiveData=MutableLiveData<NetworkResult<ClientResponse>>()
    val clientLiveData:LiveData<NetworkResult<ClientResponse>>
        get()=_clientLiveData

    private val _createLiveData= MutableLiveData<NetworkResult<AddClientResponse>>()
    val createLiveData:LiveData<NetworkResult<AddClientResponse>>
        get() = _createLiveData

    private val _statusLiveData= MutableLiveData<NetworkResult<updateClientResponse>>()
    val statusLiveData:LiveData<NetworkResult<updateClientResponse>>
        get() = _statusLiveData

    suspend fun getClients(){
        //val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiZTkxZjEwYzA4ZTdiN2JmOTVlOWFkZmZjZDlhMDNkMTkyYzAwNzk1N2Y5Mzc0YWFlOTJkMzhkZTAyNmZjMGJkNDViYTdlZGIyOTJjOTkwNmUiLCJpYXQiOjE2OTM4NDc3NjQuNTI0MjQ3ODg0NzUwMzY2MjEwOTM3NSwibmJmIjoxNjkzODQ3NzY0LjUyNDI0OTA3Njg0MzI2MTcxODc1LCJleHAiOjE3MjU0NzAxNjQuNTE2NDk0MDM1NzIwODI1MTk1MzEyNSwic3ViIjoiMiIsInNjb3BlcyI6W119.MyEl_JT13ce9uo1GpJ-jtaUnQJgjoSqV9A3yMwDlTD3Kng1DgjWInrMoikPpiUpSxysqQBAiGhvu6a0Gr0e5j3z70sD0bC7K8u8fZ-DdO6Q1_Rq15IKX1JYWdOeIsfn5REaX7xiv4eM75RX1JAiUOC3AfeOxnM1ugH6wt4wZJa-2XC-An4eweb6N4NOl7qOn9YiKq__VF_hp8f52WXGY9a5nqdTOcfsu1336zzZlLhiWiAJqPr68Ol9oEzKTu8qDLi1U7sugo8-hRHLvQqrlQcwd_gSW1l1mzH246rf8c_Mvrz0hborsgJZJQyopHZbOK835RBPGaVcqMKJaCA_ZJWBV75KVRB_zvN5FSRSnWKqTxXGAWDHuwGasOpr7eIyl857fN9EI0pDHkL6-ecwbuZbg-JBmm8uQdMZ0r38xjRr1eY_BJursTihMOnpTim7xNRcCrVNngqsSD-nryUJm4izfjqGs_RGrGqqOWFJ6jm3CWNpSOHQMGAOt1rdJDOF6exu76VvQaeUJtXC2oVzzxuQzK9FxhzB7BavLD10BJZNhfgnICJAzhZpq3BxlhqPla00AD5k7L98SnH9Z58VxSSHCgeZno_2A8zQf-DAX9GidAEM4pRX2uMGvNRhXUju2a_kKmSa9rqLyqvi61Oy8K457_NIYCHvssLBdOT3_Qa0"
        _clientLiveData.postValue(NetworkResult.Loading())
        val response=clientInterface.getClient(Constants.USER_TOKEN, 1)

        if (response.isSuccessful && response.body() != null) {
            _clientLiveData.postValue(NetworkResult.Success(response.body()!!))

            Log.d(Constraints.TAG, "login: ${response.body()}")
            Log.d("TAG", "getAllClass: ${response.body()}")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _clientLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

            Log.d(Constraints.TAG, "login: ${errorObj}")
        } else {
            _clientLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }

    suspend fun createClient(clientRequest: ClientRequest){
        //val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiZTkxZjEwYzA4ZTdiN2JmOTVlOWFkZmZjZDlhMDNkMTkyYzAwNzk1N2Y5Mzc0YWFlOTJkMzhkZTAyNmZjMGJkNDViYTdlZGIyOTJjOTkwNmUiLCJpYXQiOjE2OTM4NDc3NjQuNTI0MjQ3ODg0NzUwMzY2MjEwOTM3NSwibmJmIjoxNjkzODQ3NzY0LjUyNDI0OTA3Njg0MzI2MTcxODc1LCJleHAiOjE3MjU0NzAxNjQuNTE2NDk0MDM1NzIwODI1MTk1MzEyNSwic3ViIjoiMiIsInNjb3BlcyI6W119.MyEl_JT13ce9uo1GpJ-jtaUnQJgjoSqV9A3yMwDlTD3Kng1DgjWInrMoikPpiUpSxysqQBAiGhvu6a0Gr0e5j3z70sD0bC7K8u8fZ-DdO6Q1_Rq15IKX1JYWdOeIsfn5REaX7xiv4eM75RX1JAiUOC3AfeOxnM1ugH6wt4wZJa-2XC-An4eweb6N4NOl7qOn9YiKq__VF_hp8f52WXGY9a5nqdTOcfsu1336zzZlLhiWiAJqPr68Ol9oEzKTu8qDLi1U7sugo8-hRHLvQqrlQcwd_gSW1l1mzH246rf8c_Mvrz0hborsgJZJQyopHZbOK835RBPGaVcqMKJaCA_ZJWBV75KVRB_zvN5FSRSnWKqTxXGAWDHuwGasOpr7eIyl857fN9EI0pDHkL6-ecwbuZbg-JBmm8uQdMZ0r38xjRr1eY_BJursTihMOnpTim7xNRcCrVNngqsSD-nryUJm4izfjqGs_RGrGqqOWFJ6jm3CWNpSOHQMGAOt1rdJDOF6exu76VvQaeUJtXC2oVzzxuQzK9FxhzB7BavLD10BJZNhfgnICJAzhZpq3BxlhqPla00AD5k7L98SnH9Z58VxSSHCgeZno_2A8zQf-DAX9GidAEM4pRX2uMGvNRhXUju2a_kKmSa9rqLyqvi61Oy8K457_NIYCHvssLBdOT3_Qa0"
        _createLiveData.postValue(NetworkResult.Loading())
        val response=clientInterface.createClient(Constants.USER_TOKEN,clientRequest)
        handleResponse(response)
        Log.d("ClientRepository", "CreatedClient: ${response.body()}")
        Log.d("ClientRepository", "CreatedClient: ${response.code()}")
    }
    suspend fun updateClient(clientId: Int?, clientRequest: ClientRequest){
        _createLiveData.postValue(NetworkResult.Loading())
        val response=clientInterface.updateClient(clientId,clientRequest)
        handleUpdateResponse(response)
        Log.d("ClientRepository", "updateClient: ${response.body()}")
        Log.d("ClientRepository", "updateClient: ${response.code()}")

    }
    suspend fun deleteClient(clientId:String, clientRequest: ClientRequest){
        _createLiveData.postValue(NetworkResult.Loading())

    }

    private fun handleResponse(response: Response<AddClientResponse>){
        if (response.isSuccessful && response.body() != null){
            _createLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else{
            _createLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
    private fun handleUpdateResponse(response: Response<updateClientResponse>){
        if (response.isSuccessful && response.body() != null){
            _statusLiveData.postValue(NetworkResult.Success(response.body()!!))
        }else{
            _statusLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}