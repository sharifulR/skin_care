package com.wb.skincare

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wb.skincare.adapter.ServiceCategoryAdapter
import com.wb.skincare.databinding.FragmentCategoryServiceBinding
import com.wb.skincare.models.service.ServiceCategoryResponse
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.netwarks.ServiceInterface
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.ServiceViewModel
import com.wb.view.ServiceFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServiceCategoryFragment : Fragment() {

    private var _binding: FragmentCategoryServiceBinding?=null
    private val binding get()=_binding!!

    private val serviceViewModel by viewModels<ServiceViewModel>()

    lateinit var tokenManager: TokenManager
    /*var classId: Int = 1*/

    @Inject
    lateinit var serviceInterface: ServiceInterface
    private lateinit var serviceCategoryAdapter: ServiceCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCategoryServiceBinding.inflate(inflater,container, false)
        serviceCategoryAdapter= ServiceCategoryAdapter(::onServiceItemClicked)

        tokenManager = TokenManager(requireActivity())
        /*classId = tokenManager.classId!!*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        serviceViewModel.getServicesCategory()

        binding.serviceListRecyclerView.layoutManager=
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.serviceListRecyclerView.adapter=serviceCategoryAdapter


        activity?.onBackPressedDispatcher?.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                   // clearBackStack()

                    val builder:AlertDialog.Builder =AlertDialog.Builder(context!!)
                    builder.setTitle("")
                    builder.setMessage("")
                    builder.setPositiveButton("Ok",DialogInterface.OnClickListener { dialogInterface, i ->

                    })

                }
            })

    }

    private fun bindObservers() {
        serviceViewModel.serviceCategoryLiveData.observe(viewLifecycleOwner, Observer {
            //binding.progressBar.isVisible=false

            when(it){
                is NetworkResult.Success->{
                    Log.d("TAG", "bindObservers: ${it.data}")
                    val r = it.data
                    serviceCategoryAdapter.submitList(it.data?.serviceCategoryData?.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    //binding.progressBar.isVisible=true
                }
            }
        })
    }

    private fun onServiceItemClicked(serviceResponse: ServiceCategoryResponse.ServiceCategoryData.Data){

        val serviceId=serviceResponse.id
        val serviceName = serviceResponse.categoryName
        tokenManager.serviceCategoryId=serviceId
        tokenManager.userName = serviceName
        replaceFragment(ServiceFragment())
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