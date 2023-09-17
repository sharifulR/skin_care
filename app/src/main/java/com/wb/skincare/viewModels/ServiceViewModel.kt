package com.wb.skincare.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wb.skincare.repositorys.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ServiceViewModel @Inject constructor(private val serviceRepository:ServiceRepository): ViewModel() {
    val serviceLiveData get()=serviceRepository.serviceLiveData

    fun getServices(){
        viewModelScope.launch {
            serviceRepository.getServices()
        }
    }
}