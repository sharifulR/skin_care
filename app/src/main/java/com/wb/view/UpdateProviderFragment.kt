package com.wb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wb.skincare.R
import com.wb.skincare.databinding.FragmentUpdateProviderBinding
import com.wb.skincare.models.provider.ProviderRequest
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.ProviderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProviderFragment : Fragment() {
    private var _binding:FragmentUpdateProviderBinding?=null
    private val binding get() = _binding!!

    private val providerViewModel by viewModels<ProviderViewModel>()

    private lateinit var tokenManager: TokenManager
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding= FragmentUpdateProviderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialData()
    }

    private fun setInitialData() {
        tokenManager= TokenManager(requireContext())
        progressDialog = ProgressDialog(requireContext())


        bindObservers()

        val name = tokenManager.userName

        if(name!=null){
            binding.firstNameIEtvId.editText!!.setText(name)
        }

        clickListener()
    }

    private fun clickListener() {
        binding.providerUpdateBtnId.setOnClickListener {
            val id=tokenManager.clientId
            val name = binding.firstNameIEtvId.editText!!.text.toString()


            if (name.isEmpty()) {
                binding.firstNameIEtvId.error = "Field can not be empty"
                // binding.firstNameIEtvId.requestFocus()
                return@setOnClickListener
            }

            val providerRequest= ProviderRequest(name)
            providerViewModel.updateProvider(id,providerRequest)

        }
    }

    private fun bindObservers() {
        providerViewModel.updateProviderLiveData.observe(viewLifecycleOwner, Observer {
            progressDialog.show()
            when(it){
                is NetworkResult.Success->{
                    progressDialog.dismiss()
                    replaceFragment(ProviderFragment())
                }
                is NetworkResult.Error->{

                }
                is NetworkResult.Loading->{
                    progressDialog.show()

                }
            }
        })
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction=fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction?.addToBackStack(null)?.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}