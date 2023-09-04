package com.wb.skincare

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.wb.skincare.databinding.FragmentProfileBinding
import com.wb.skincare.utils.CommonMethods
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.utils.TokenManager
import com.wb.view.AboutFragment
import com.wb.view.ChangePasswordFragment
import com.wb.view.LoginFragment
import com.wb.view.UpdateProfileFragment
import com.wb.wbsoftware.networks.ApiInterface
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    var progressDialog: ProgressDialog? = null
    lateinit var tokenManager: TokenManager
    lateinit var commonMethods: CommonMethods


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        initView()
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView(){
        commonMethods = CommonMethods(requireActivity());
        tokenManager = TokenManager(requireActivity())
        progressDialog = ProgressDialog(requireActivity())



        binding.chnagePasswordId.setOnClickListener {
            replaceFragment(ChangePasswordFragment())
        }
        binding.aboutId.setOnClickListener {
            replaceFragment(AboutFragment())
        }

        binding.updateUserInfoId.setOnClickListener {
            replaceFragment(UpdateProfileFragment())
        }


        binding.logoutId.setOnClickListener {

            tokenManager.isLogin = false
            replaceFragment(LoginFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction=fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction?.commit()
    }
}