package com.wb.skincare.repositorys

import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wb.skincare.models.LoginRequest
import com.wb.skincare.models.LoginResponse
import com.wb.skincare.netwarks.NetworkResult
import com.wb.wbsoftware.networks.ApiInterface
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiInterface: ApiInterface) {

    
    private val _loginResponseLiveData = MutableLiveData<NetworkResult<LoginResponse>>()
    val loginResponseLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = _loginResponseLiveData

    suspend fun login(loginRequest: LoginRequest){
        //loading progress
        _loginResponseLiveData.postValue(NetworkResult.Loading())

        //
        val response= apiInterface.login(loginRequest)
        Log.d(TAG, "login: ${response.code()}")
        Log.d(TAG, "login: ${response.message()}")

        handleResponse(response)
    }

    private fun handleResponse(response: Response<LoginResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _loginResponseLiveData.postValue(NetworkResult.Success(response.body()!!))

            Log.d(TAG, "login: ${response.body()}")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            //_loginResponseLiveData.postValue(NetworkResult.Error(response.message(), response.body()))
            _loginResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

            Log.d(TAG, "login: ${errorObj}")
        } else {
            _loginResponseLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }
}