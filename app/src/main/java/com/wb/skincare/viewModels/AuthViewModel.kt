package com.wb.skincare.viewModels

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wb.skincare.models.LoginRequest
import com.wb.skincare.models.LoginResponse
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.repositorys.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel(){

    val userRepositoryLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = userRepository.loginResponseLiveData

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch{
            userRepository.login(loginRequest)

        }
    }

    fun validateCredentials(mobile:String,password:String, isLogin:Boolean): Pair<Boolean,String>{
        var result=Pair(true,"")
        if (!isLogin && TextUtils.isEmpty(mobile)|| TextUtils.isEmpty(password)){
            result=Pair(false,"Please provide the credential")
        }
        else if (password.length<=2){
            result=Pair(false,"Password length should be getter than 2")

        }
        return result

    }


}