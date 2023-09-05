package com.wb.skincare.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wb.skincare.models.ClientRequest
import com.wb.skincare.repositorys.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(private val clientRepository: ClientRepository):ViewModel(){

    val clientLiveData get()=clientRepository.clientLiveData
    val statusLiveData get() = clientRepository.statusLiveData

    fun getClients(){
        viewModelScope.launch {
            clientRepository.getClients()
        }
    }

    fun createClient(clientRequest: ClientRequest){
        viewModelScope.launch {
            clientRepository.createClient(clientRequest)
        }
    }
    fun updateClient(clientId: Int?, clientRequest: ClientRequest){
        viewModelScope.launch {
            clientRepository.updateClient(clientId,clientRequest)
        }
    }
}