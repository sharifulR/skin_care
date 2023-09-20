package com.wb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wb.skincare.R
import com.wb.skincare.databinding.FragmentAddBranchBinding
import com.wb.skincare.models.branch.BranchRequest
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.BranchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBranchFragment : Fragment() {
    private var _binding:FragmentAddBranchBinding?=null
    private val binding get() = _binding!!

    private val branchViewModel by viewModels<BranchViewModel>()

    lateinit var tokenManager: TokenManager
    private lateinit var progressDialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAddBranchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialData()
    }


    private fun setInitialData() {
        bindObservers()
        clickListener()
        progressDialog = ProgressDialog(requireContext())

    }

    private fun clickListener() {
        binding.btnAdd.setOnClickListener {
            //val authToken=tokenManager.authToken
            val name = binding.nameIdLayout.editText!!.text.toString()
            val address = binding.addressLayout.editText!!.text.toString()


            if (name.isEmpty()) {
                binding.nameId.error = "Field can not be empty"
                binding.nameId.requestFocus()
                return@setOnClickListener
            }
            if (address.isEmpty()) {
                binding.addressId.error = "Field can not be empty"
                return@setOnClickListener
            }

            val branchRequest= BranchRequest(address,name)
            branchViewModel.createBranch(branchRequest)


        }
    }

    private fun bindObservers() {
        branchViewModel.createBranchLiveData.observe(viewLifecycleOwner, Observer {

            progressDialog.dismiss()

            when(it){
                is NetworkResult.Success ->{
                    //Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    replaceFragment(BranchFragment())
                }
                is NetworkResult.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
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