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
import com.wb.skincare.adapter.ClientAdapter
import com.wb.skincare.adapter.ProviderAdapter
import com.wb.skincare.databinding.FragmentProviderBinding
import com.wb.skincare.models.ClientResponse
import com.wb.skincare.models.ProviderResponse
import com.wb.skincare.netwarks.ClientInterface
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.netwarks.ProviderInterface
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.ClientViewModel
import com.wb.skincare.viewModels.ProviderViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.security.Provider
import javax.inject.Inject

@AndroidEntryPoint
class ProviderFragment : Fragment() {

    private var _binding:FragmentProviderBinding?=null
    private val binding get() = _binding!!

    private val providerViewModel by viewModels<ProviderViewModel>()

    lateinit var tokenManager: TokenManager
    private lateinit var progressDialog: ProgressDialog

    @Inject
    lateinit var providerInterface: ProviderInterface
    private lateinit var providerAdapter: ProviderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentProviderBinding.inflate(inflater,container,false)

        providerAdapter= ProviderAdapter(::onProviderItemClicked)

        tokenManager = TokenManager(requireActivity())
        progressDialog = ProgressDialog(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        providerViewModel.getProviders()
        bindObservers()
        binding.ProviderRecyclerViewId.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.ProviderRecyclerViewId.adapter=providerAdapter

        binding.addProviderFloatingActionButton.setOnClickListener {
            replaceFragment(AddProviderFragment())

        }
    }

    private fun bindObservers() {
        providerViewModel.providerLiveData.observe(viewLifecycleOwner, Observer {
            progressDialog.show()

            when(it){
                is NetworkResult.Success->{
                    progressDialog.dismiss()
                    Log.d("TAG", "bindObservers: ${it.data}")
                    val r = it.data
                    providerAdapter.submitList(it.data?.providerData?.data)
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

    private fun onProviderItemClicked(providerResponse: ProviderResponse.ProviderData.Data){

        val providerId=providerResponse.id
        val providerName = providerResponse.providerName
        /*val providerMobile = providerResponse.providerMobile
        val providerEmail = providerResponse.providerEmail
        val providerAddress = providerResponse.providerAddress*/
        tokenManager.clientId=providerId
        tokenManager.userName = providerName
        /*tokenManager.mobile = providerMobile.toString()
        tokenManager.userEmail=providerEmail.toString()
        tokenManager.userAddress=providerAddress.toString()*/
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