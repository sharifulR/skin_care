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
import com.wb.skincare.databinding.FragmentUpdateProfileBinding
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.netwarks.UserInfoInterface
import com.wb.skincare.viewModels.UserInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateProfileFragment : Fragment() {

    private var _binding: FragmentUpdateProfileBinding?=null
    private val binding get()=_binding!!

    private val userInfoViewModel by viewModels<UserInfoViewModel>()

    @Inject
    lateinit var userInfoInterface: UserInfoInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentUpdateProfileBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {



    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}