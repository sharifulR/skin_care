package com.wb.skincare.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wb.skincare.repositorys.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(private val userInfoRepository: UserInfoRepository) : ViewModel(){

    val userInfoLiveData get()=userInfoRepository.userInfoLiveData

    fun getUserInfo(){
        viewModelScope.launch {
            userInfoRepository.getUserInfo()
        }
    }
}