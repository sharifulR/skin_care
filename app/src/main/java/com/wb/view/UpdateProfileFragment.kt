package com.wb.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.wb.skincare.ProfileFragment
import com.wb.skincare.databinding.FragmentUpdateProfileBinding
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.netwarks.UserInfoInterface
import com.wb.skincare.utils.TokenManager
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
    private lateinit var tokenManager: TokenManager

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

        setInitialData()
    }

    private fun setInitialData() {
        tokenManager= TokenManager(requireContext())


        val name = tokenManager.userName
        val mobile = tokenManager.userMobile
        val email=tokenManager.userEmail

        if(name!=null){
            binding.firstNameIEtvId.editText!!.setText(name)
        }
        if(mobile!=null){
            binding.phoneInputEditTextId.editText!!.setText(mobile)
        }
        if(email!=null){
            binding.emailIEtvId.editText!!.setText(email)
        }
        clickListener()
    }

    private fun bindObservers() {
        userInfoViewModel.userInfoLiveData.observe(viewLifecycleOwner, Observer {

            //progressDialog.dismiss()

            when(it){
                is NetworkResult.Success ->{

                    //tokenManager.saveToken(it.data!!.accessToken)
                   // tokenManager.isLogin = true


                    startActivity(Intent(requireActivity(), ProfileFragment::class.java))
                }
                is NetworkResult.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    //progressDialog.show()

                }
            }
        })
    }

    private fun clickListener() {
        binding.btnProfileId.setOnClickListener {

            val name = binding.firstNameIEtvId.editText!!.text.toString()
            val mobile = binding.phoneInputEditTextId.editText!!.text.toString()
            val email = binding.emailIEtvId.editText!!.text.toString()


            if (name.isEmpty()) {
                binding.firstNameIEtvId.error = "Field can not be empty"
                // binding.firstNameIEtvId.requestFocus()
                return@setOnClickListener
            }
            if (mobile.isEmpty()) {
                binding.phoneInputEditTextId.error = "Field can not be empty"
                // binding.firstNameIEtvId.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.emailIEtvId.error = "Field can not be empty"
                // binding.firstNameIEtvId.requestFocus()
                return@setOnClickListener
            }
            if (!isValidMobileNo(mobile)) {
                binding.phoneInputEditTextId.error = "Invalid phone number"
                return@setOnClickListener
            }

            if (!validateEmail(email)) {
                binding.emailIEtvId.error = "Email not valid"
                // binding.firstNameIEtvId.requestFocus()
                return@setOnClickListener
            }

            tokenManager.userName = name
            tokenManager.mobile = mobile
            tokenManager.userEmail = email

            userInfoViewModel.updateUserInfo(
            name,mobile, email
            )

            bindObservers()

            /*try {
                *//*val jsonObject = createJson(
                    name,
                    email,
                    mobile
                )
                val jsonParser = JsonParser()
                val gsonObject = jsonParser.parse(jsonObject) as JsonObject
                Log.v("json", jsonObject)*//*
                tokenManager.userName = name
                tokenManager.mobile = mobile
                tokenManager.userEmail = email
                //binding.progressBar.visibility = View.VISIBLE
                //setProgressValue(0)
                userInfoViewModel.updateUserInfo(
                    *//*gsonObject,*//*
                    *//*tokenManager.authToken!!,*//*
                name,mobile, email

                )


            } catch (e: Exception) {
                e.printStackTrace()
            }*/

        }
    }

    private fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
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

    fun createJson(
        name: String,
        email: String,
        mobile: String
    ): String {
        val model: Model = Model()
        model.name = name
        model.email = email
        model.mobile = mobile
        val json = Gson().toJson(model)
        return json
    }

    class Model {
        var name: String? = null
        var email: String? = null
        var mobile: String? = null

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}