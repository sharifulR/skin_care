package com.wb.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.wb.skincare.MainActivity
import com.wb.skincare.R
import com.wb.skincare.databinding.FragmentLoginBinding
import com.wb.skincare.models.LoginRequest
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.CommonMethods
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get()=_binding!!

    private val authViewModel by viewModels<AuthViewModel>()

    @Inject
    lateinit var tokenManager: TokenManager
    lateinit var commonMethods: CommonMethods
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentLoginBinding.inflate(inflater,container, false)

        initView()

        return binding.root
        }

    private fun initView() {
        tokenManager=TokenManager(requireContext())
        progressDialog = ProgressDialog(requireActivity())


        commonMethods = CommonMethods(requireActivity())

        binding.btnSignIn.setOnClickListener {
            val userMobile = binding.edtEmailPhone.editText!!.text.toString()
            val password = binding.edtPassword.editText!!.text.toString()

            if (userMobile.isEmpty()) {
                binding.edtEmailPhone.error = "মোবাইল নম্বর লিখুন "
                binding.edtEmailPhone.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.edtPassword.error = "পাসওয়ার্ড লিখুন"
                return@setOnClickListener
            }


            if (commonMethods.isOnline()) {
                tokenManager.mobile = userMobile
                progressDialog.show()

                authViewModel.login(getLoginRequest())

            } else {
                commonMethods.showMatDialog("দুঃখিত", getString(R.string.check_internet))
            }
            /*val validationResult= validateUserInput()
            if (validationResult.first){

            }
            else{
                Toast.makeText(context,validationResult.second, Toast.LENGTH_SHORT).show()
            }*/
    }

    binding.edtEmailPhone.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {



                if (!isValidMobileNo(s.toString())) {
                    binding.edtEmailPhone.isErrorEnabled = true
                    binding.edtEmailPhone.error = "মোবাইল নম্বর দিন "
                } else if (s.toString().length!=11) {
                    binding.edtEmailPhone.isErrorEnabled = true
                    binding.edtEmailPhone.error = "মোবাইল নম্বর কমপক্ষে ১১ ডিজিট হবে "

                }
                else {
                    binding.edtEmailPhone.isErrorEnabled = true
                    binding.edtEmailPhone.error = null
                }

            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.edtPassword.editText!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length < 3) {
                    binding.edtPassword.isErrorEnabled = true
                    binding.edtPassword.error = "পাসওয়ার্ড কমপক্ষে ৩ ডিজিট লাগবে "
                } else {
                    binding.edtPassword.error = null
                    binding.edtPassword.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })




    }
    fun isValidMobileNo(bdNumberStr: String?): Boolean {

        val phoneUtil = PhoneNumberUtil.getInstance()
        val isValid: Boolean = try {

            val bdNumberProto = phoneUtil.parse(bdNumberStr, "BD")
            phoneUtil.isValidNumber(bdNumberProto) // returns true
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: $e")
            false
        }
        return isValid
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObseres()
    }
    private fun getLoginRequest(): LoginRequest {
        val mobile=binding.edtEmailPhone.editText!!.text.toString()
        val password=binding.edtPassword.editText!!.text.toString()
        return LoginRequest(mobile,password)
    }

    private fun bindObseres() {
        authViewModel.userRepositoryLiveData.observe(viewLifecycleOwner, Observer {

            progressDialog.dismiss()

            when(it){
                is NetworkResult.Success ->{

                    tokenManager.saveToken(it.data!!.accessToken)
                    tokenManager.isLogin = true


                    startActivity(Intent(requireActivity(),MainActivity::class.java))
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

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}