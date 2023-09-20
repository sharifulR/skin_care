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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wb.skincare.R
import com.wb.skincare.adapter.ServiceAdapter
import com.wb.skincare.databinding.FragmentServiceBinding
import com.wb.skincare.models.service.CategoryWiseService
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.netwarks.ServiceInterface
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.ServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServiceFragment : Fragment() {

    private var _binding: FragmentServiceBinding?=null
    private val binding get()=_binding!!

    private val serviceViewModel by viewModels<ServiceViewModel>()

    lateinit var tokenManager: TokenManager
    var serviceCategoryId: Int = 1

    @Inject
    lateinit var serviceInterface: ServiceInterface
    private lateinit var serviceAdapter: ServiceAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentServiceBinding.inflate(inflater,container, false)
        serviceAdapter= ServiceAdapter(::onServiceItemClicked)

        tokenManager = TokenManager(requireActivity())
        serviceCategoryId = tokenManager.serviceCategoryId!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        serviceViewModel.getCategoryWiseService(serviceCategoryId)

        binding.serviceListRecyclerView.layoutManager=
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.serviceListRecyclerView.adapter=serviceAdapter

        /*binding.addClientFloatingActionButton.setOnClickListener {
            replaceFragment(AddClientFragment())

        }*/
    }

    private fun bindObservers() {
        serviceViewModel.categoryWiseServiceLiveData.observe(viewLifecycleOwner, Observer {
            //binding.progressBar.isVisible=false

            when(it){
                is NetworkResult.Success->{
                    Log.d("TAG", "bindObservers: ${it.data}")
                    val r = it.data
                    if (it.data!=null){
                        serviceAdapter.submitList(it.data?.serviceData)

                    }else {
                        Toast.makeText(context, "data not found!", Toast.LENGTH_SHORT).show()
                    }
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

    private fun onServiceItemClicked(serviceResponse: CategoryWiseService.ServiceData){
        /*val bundle=Bundle()
        bundle.putString("client",Gson().toJson(clientResponse))*/

        val serviceId=serviceResponse.id
        val serviceName = serviceResponse.serviceName
        val servicePrice = serviceResponse.servicePrice
        tokenManager.serviceCategoryId=serviceId
        tokenManager.userName = serviceName
        tokenManager.mobile = servicePrice.toString()
       // replaceFragment(ServiceFragment())
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