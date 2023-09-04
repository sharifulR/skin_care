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
import com.wb.skincare.netwarks.ClientInterface
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.viewModels.ClientViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ClientFragment : Fragment() {

    private var _binding: FragmentClientBinding?=null
    private val binding get()=_binding!!

    private val clientViewModel by viewModels<ClientViewModel>()

    @Inject
    lateinit var clientInterface: ClientInterface
    private lateinit var clientAdapter: ClientAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentClientBinding.inflate(inflater,container, false)
        clientAdapter= ClientAdapter()

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

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}