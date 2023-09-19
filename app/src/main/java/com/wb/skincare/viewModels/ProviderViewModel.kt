package com.wb.skincare.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wb.skincare.models.ClientRequest
import com.wb.skincare.models.ProviderRequest
import com.wb.skincare.repositorys.ProviderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.security.Provider
import javax.inject.Inject

@HiltViewModel
class ProviderViewModel @Inject constructor(private val providerRepository:ProviderRepository):
    ViewModel() {

    val providerLiveData get()=providerRepository.providerLiveData
    val createProviderLiveData get()=providerRepository.createProviderLiveData
    val updateProviderLiveData get() = providerRepository.updateProviderLiveData

    fun createProvider(providerRequest: ProviderRequest){
        viewModelScope.launch {
            providerRepository.createProvider(providerRequest)
        }
    }

    fun getProviders(){
        viewModelScope.launch {
            providerRepository.getProviders()
        }
    }

    fun updateProvider(providerId: Int?, providerRequest: ProviderRequest){
        viewModelScope.launch {
            providerRepository.updateProvider(providerId,providerRequest)
        }
    }
}