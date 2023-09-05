package com.wb.skincare

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wb.skincare.databinding.FragmentProfileBinding
import com.wb.skincare.databinding.FragmentUpdateProfileBinding
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.netwarks.UserInfoInterface
import com.wb.skincare.utils.CommonMethods
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.UserInfoViewModel
import com.wb.view.AboutFragment
import com.wb.view.ChangePasswordFragment
import com.wb.view.LoginFragment
import com.wb.view.UpdateProfileFragment
import com.wb.wbsoftware.networks.ApiInterface
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    var progressDialog: ProgressDialog? = null
    lateinit var tokenManager: TokenManager
    lateinit var commonMethods: CommonMethods

    private val userInfoViewModel by viewModels<UserInfoViewModel>()

    @Inject
    lateinit var userInfoInterface: UserInfoInterface

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


        userInfoViewModel.getUserInfo()
        bindObservers()

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

    private fun bindObservers() {
        userInfoViewModel.userInfoLiveData.observe(viewLifecycleOwner, Observer {
            //binding.progressBar.isVisible=false

            when(it){
                is NetworkResult.Success->{
                    Log.d("TAG", "bindObservers: ${it.data}")
                    val name = it.data?.userData?.name
                    val mobile = it.data?.userData?.mobile
                    val email = it.data?.userData?.email
                    tokenManager.userName = name
                    tokenManager.userMobile = mobile
                    tokenManager.userEmail = email
                    binding.txvProfileName.text = name!!.replace("\"", "")

                    /*binding.firstNameIEtvId.editText!!.setText(it.data?.userData?.name)
                    binding.phoneInputEditTextId.editText!!.setText(it.data?.userData?.mobile)
                    binding.emailIEtvId.editText!!.setText(it.data?.userData?.mobile)*/
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    //binding.progressBar.isVisible=true
                }

                else -> {}
            }
        })
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction=fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction?.commit()
    }
}