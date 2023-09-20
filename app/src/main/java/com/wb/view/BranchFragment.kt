package com.wb.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wb.skincare.R
import com.wb.skincare.adapter.BranchAdapter
import com.wb.skincare.databinding.FragmentBranchBinding
import com.wb.skincare.models.branch.BranchResponse
import com.wb.skincare.netwarks.BranchInterface
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.BranchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BranchFragment : Fragment() {
    private var _binding:FragmentBranchBinding?=null
    private val binding get() = _binding!!

    private val branchViewModel by viewModels<BranchViewModel>()

    lateinit var tokenManager: TokenManager
    private lateinit var progressDialog: ProgressDialog

    @Inject
    lateinit var branchInterface: BranchInterface
    private lateinit var branchAdapter: BranchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentBranchBinding.inflate(inflater,container,false)

        branchAdapter= BranchAdapter(::onProviderItemClicked)

        tokenManager = TokenManager(requireActivity())
        progressDialog = ProgressDialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        branchViewModel.getBranch()
        bindObservers()
        binding.BranchRecyclerViewId.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.BranchRecyclerViewId.adapter=branchAdapter

        binding.addProviderFloatingActionButton.setOnClickListener {
            replaceFragment(AddBranchFragment())

        }
    }
    private fun bindObservers() {
        branchViewModel.branchLiveData.observe(viewLifecycleOwner, Observer {
            progressDialog.show()

            when(it){
                is NetworkResult.Success->{
                    progressDialog.dismiss()
                    Log.d("TAG", "bindObservers: ${it.data}")
                    val r = it.data
                    branchAdapter.submitList(it.data?.branchData?.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    progressDialog.show()
                }
            }
        })
    }

    private fun onProviderItemClicked(branchResponse: BranchResponse.BranchData.Data){

        val providerId=branchResponse.id
        val providerName = branchResponse.branchTitle
        val providerAddress = branchResponse.branchAddress
        tokenManager.clientId=providerId
        tokenManager.userName = providerName
        replaceFragment(UpdateProviderFragment())
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction=fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView,fragment,null)
        fragmentTransaction?.addToBackStack(null)?.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}