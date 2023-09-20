package com.wb.skincare.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wb.skincare.models.branch.BranchRequest
import com.wb.skincare.repositorys.BranchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BranchViewModel @Inject constructor(private val branchRepository: BranchRepository):
    ViewModel(){

    val createBranchLiveData get()=branchRepository.createBranchLiveData
    val branchLiveData get()=branchRepository.branchLiveData

    fun createBranch(branchRequest: BranchRequest){
        viewModelScope.launch {
            branchRepository.createBranch(branchRequest)
        }
    }

    fun getBranch(){
        viewModelScope.launch {
            branchRepository.getBranch()
        }
    }
}