package com.wb.skincare.repositorys

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wb.skincare.models.UserInfo
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.netwarks.UserInfoInterface
import com.wb.skincare.utils.Constants
import org.json.JSONObject
import javax.inject.Inject

class UserInfoRepository @Inject constructor(private val userInfoInterface: UserInfoInterface) {
    private val _userInfoLiveData= MutableLiveData<NetworkResult<UserInfo>>()
    val userInfoLiveData: LiveData<NetworkResult<UserInfo>>
        get() = _userInfoLiveData


    private val _statusLiveData=MutableLiveData<NetworkResult<String>>()
    val statusLiveData:LiveData<NetworkResult<String>>
        get() = _statusLiveData
    suspend fun getUserInfo(){

        val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiYzJjMjc2Y2YzMzA0MmM0ODhmOTkxMDY4MzQyYjcwYjY3ZGJlNTNlMjljZDU1ZTY0YTRiMmY1NDk4NDg4YTk5OWRmODlhZGYxZDczZWUwZTkiLCJpYXQiOjE2OTM4MTI0NTUuMTM1MTIyMDYwNzc1NzU2ODM1OTM3NSwibmJmIjoxNjkzODEyNDU1LjEzNTEyMzk2ODEyNDM4OTY0ODQzNzUsImV4cCI6MTcyNTQzNDg1NS4xMjQxNTc5MDU1Nzg2MTMyODEyNSwic3ViIjoiMiIsInNjb3BlcyI6W119.iH7tkFXd9-jvb_NgQ81nJNVyY1sVrsM7DEZCuAEEYh_aY_g0F4LtcVKeHGW-m3S6HGljw96i3fiLAdw8VjuEw4Q0d74VhKFH60r_Qwzf-0UpBo-_dkbdtvvIHsNB5E4FY_rIjMuXGFFVyIiV4FqXIrbxKMPTe_52Pt7yg3fKDPo07ux50HaOV5wYLDSCjXcDn2cHECet7trwpyGCKWcpfbKnDs0qU8g2OfynIiOXxPXveromVRMj0M63UhzTkqzUMk7d3dSqotz5YVSrKe2OtWVGZU2h9QwjynWY0oJld92cadqlsatNF3Hni5kIia9ZdzVrfQCyoqipa_JVV2OGKFQn9wYmXWTGMn5gu-pSKaf5tQ1gFx5air6fD6tzrNi53r5kpOGnUp1evwY8l_Yrec7Fq6f7Aq0IUtGH5Q54un_DCURK0gBSwR3qzxGMZHM57ii7EN7J1-7HTI1QYtbu269Y2Mgascrvv7Q5jjIIZwWjZW13mZuJd8vM3LXdJLm_HsGiEViSnWtWESsbYo9dMjgz3ncOXLjQeBzSUVRx3kBV4OhdY98AMPg0uZaqmyICyTFXFgX-kXI64OlgTDfOuaRM-peiP2xVf4jJSdTpAC0mlhkwIW_mZB3tslmevcARf81g9X6EcdBlCV4fuNrK91hIEu-No-yzBpImG-qMxs0"
        _userInfoLiveData.postValue(NetworkResult.Loading())
        var response=userInfoInterface.getUserInfo(Constants.USER_TOKEN)

        if (response.isSuccessful && response.body() != null) {
            _userInfoLiveData.postValue(NetworkResult.Success(
                response.body()!!)
            )

            Log.d(Constraints.TAG, "UserInfo: ${response.body()}")
            Log.d("TAG", "getUserInfo: ${response.body()}")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userInfoLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

            Log.d(Constraints.TAG, "UserInfo: ${errorObj}")
        } else {
            _userInfoLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }

    suspend fun updateUserInfo(name: String, mobile:String, email:String) {
        _userInfoLiveData.postValue(NetworkResult.Loading())
        var response=userInfoInterface.updateUserInfo(Constants.USER_TOKEN,name,mobile,email)

        if (response.isSuccessful && response.body() != null) {
            _userInfoLiveData.postValue(NetworkResult.Success(
                response.body()!!)
            )

            Log.d(Constraints.TAG, "UserInfo: ${response.body()}")
            Log.d("TAG", "getUserInfo: ${response.body()}")
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userInfoLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))

            Log.d(Constraints.TAG, "UserInfo: ${errorObj}")
        } else {
            _userInfoLiveData.postValue(NetworkResult.Error("Something went wrong!"))
        }
    }

}