package com.wb.view

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.wb.skincare.ClientFragment
import com.wb.skincare.R
import com.wb.skincare.databinding.FragmentAddClientBinding
import com.wb.skincare.models.client.ClientRequest
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.viewModels.ClientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientFragment : Fragment() {

    private var _binding: FragmentAddClientBinding?=null
    private val binding get()=_binding!!

    private val clientViewModel by viewModels<ClientViewModel>()
    private lateinit var progressDialog: ProgressDialog

   // private lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAddClientBinding.inflate(inflater,container, false)

        setInitialData()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    private fun setInitialData() {
        bindObservers()
        clickListener()
        progressDialog = ProgressDialog(requireContext())

        binding.emailId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) {
                    if (!isValidEmail(s.toString())) {
                        binding.emailLayout.error= "Email not valid"
                    }else{
                        binding.emailLayout.error = null
                        binding.emailLayout.isErrorEnabled = false
                    }

                }
                if (s.isEmpty()) {
                    binding.emailLayout.error = null
                    binding.emailLayout.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun clickListener() {
        binding.btnAdd.setOnClickListener {
            //val authToken=tokenManager.authToken
            val name = binding.nameIdLayout.editText!!.text.toString()
            val mobile = binding.mobile1Layout.editText!!.text.toString()
            val email = binding.emailLayout.editText!!.text.toString()
            val address = binding.addressLayout.editText!!.text.toString()


            if (name.isEmpty()) {
                binding.nameId.error = "Field can not be empty"
                binding.nameId.requestFocus()
                return@setOnClickListener
            }
            if (mobile.isEmpty()) {
                binding.mobileId.error = "Field can not be empty"
                binding.mobileId.requestFocus()
                return@setOnClickListener
            }

            if (!isValidMobileNo(mobile)) {
                binding.mobileId.error = "Invalid phone number"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.emailId.error = "Field can not be empty"
                binding.emailId.requestFocus()
                return@setOnClickListener
            }
            if (address.isEmpty()) {
                binding.addressId.error = "Field can not be empty"
                return@setOnClickListener
            }

            val clientRequest= ClientRequest(address,email,mobile,name)
            clientViewModel.createClient(clientRequest)


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

    private fun bindObservers() {
        clientViewModel.createLiveData.observe(viewLifecycleOwner, Observer {

            progressDialog.dismiss()

            when(it){
                is NetworkResult.Success ->{
                    //Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    replaceFragment(ClientFragment())
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
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction=fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction?.addToBackStack(null)?.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}