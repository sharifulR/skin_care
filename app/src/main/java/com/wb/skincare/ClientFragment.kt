package com.wb.skincare

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
import com.wb.skincare.adapter.ClientAdapter
import com.wb.skincare.databinding.FragmentClientBinding
import com.wb.skincare.models.ClientResponse
import com.wb.skincare.netwarks.ClientInterface
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.ClientViewModel
import com.wb.view.UpdateClientFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ClientFragment : Fragment() {

    private var _binding: FragmentClientBinding?=null
    private val binding get()=_binding!!

    private val clientViewModel by viewModels<ClientViewModel>()

    lateinit var tokenManager: TokenManager

    @Inject
    lateinit var clientInterface: ClientInterface
    private lateinit var clientAdapter: ClientAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentClientBinding.inflate(inflater,container, false)
        clientAdapter= ClientAdapter(::onClientItemClicked)

        tokenManager = TokenManager(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clientViewModel.getClients()
        bindObservers()
        binding.clientRecyclerViewId.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.clientRecyclerViewId.adapter=clientAdapter
    }

    private fun bindObservers() {
        clientViewModel.clientLiveData.observe(viewLifecycleOwner, Observer {
            //binding.progressBar.isVisible=false

            when(it){
                is NetworkResult.Success->{
                    Log.d("TAG", "bindObservers: ${it.data}")
                    val r = it.data
                    clientAdapter.submitList(it.data?.clientData?.data)
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

    private fun onClientItemClicked(clientResponse: ClientResponse.ClientData.Data){
        /*val bundle=Bundle()
        bundle.putString("client",Gson().toJson(clientResponse))*/

        val clientId=clientResponse.id
        val clientName = clientResponse.clientName
        val clientMobile = clientResponse.clientMobile
        tokenManager.clientId=clientId
        tokenManager.userName = clientName
        tokenManager.mobile = clientMobile
        replaceFragment(UpdateClientFragment())
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction=fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction?.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}