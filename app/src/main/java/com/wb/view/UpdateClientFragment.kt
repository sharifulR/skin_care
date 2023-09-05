package com.wb.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.wb.skincare.ClientFragment
import com.wb.skincare.R
import com.wb.skincare.databinding.FragmentClientBinding
import com.wb.skincare.databinding.FragmentUpdateClientBinding
import com.wb.skincare.models.ClientRequest
import com.wb.skincare.models.ClientResponse
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.TokenManager
import com.wb.skincare.viewModels.ClientViewModel

class UpdateClientFragment : Fragment() {
    private var _binding: FragmentUpdateClientBinding?=null
    private val binding get()=_binding!!

    //private val clientViewModel by viewModels<ClientViewModel>()

    private lateinit var tokenManager: TokenManager
    private var clientResponse:ClientRequest?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentUpdateClientBinding.inflate(inflater,container, false)

        setInitialData()
        return binding.root
    }

    private fun setInitialData() {
        tokenManager= TokenManager(requireContext())


        //bindObservers()

        val name = tokenManager.userName
        val mobile = tokenManager.mobile

        if(name!=null){
            binding.firstNameIEtvId.editText!!.setText(name)
        }
        if(mobile!=null){
            binding.phoneInputEditTextId.editText!!.setText(mobile)
        }
        //clickListener()
    }

    private fun clickListener() {
        binding.clientUpdateBtnId.setOnClickListener {
            val id=tokenManager.clientId
            val name = binding.firstNameIEtvId.editText!!.text.toString()
            val mobile = binding.phoneInputEditTextId.editText!!.text.toString()


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

            if (!isValidMobileNo(mobile)) {
                binding.phoneInputEditTextId.error = "Invalid phone number"
                return@setOnClickListener
            }

            val clientRequest=ClientRequest("","",mobile,name)
            //clientViewModel.updateClient(id,clientRequest)



//            if (imageFile != null) {
//                val imagePath: RequestBody =
//                    RequestBody.create(MediaType.parse("application/octet-stream"), imageFile);
//                imageBody = MultipartBody.Part.createFormData(
//                    "photo",
//                    imageFile!!.getName(),
//                    imagePath
//                );


        }
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

    /*private fun bindObservers() {
        clientViewModel.statusLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success->{
                    startActivity(Intent(context,ClientFragment::class.java))
                }
                is NetworkResult.Error->{

                }
                is NetworkResult.Loading->{

                }
            }
        })
    }*/

}