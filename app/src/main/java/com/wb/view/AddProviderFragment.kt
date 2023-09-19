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
import com.wb.skincare.databinding.FragmentAddProviderBinding
import com.wb.skincare.models.ClientRequest
import com.wb.skincare.models.ProviderRequest
import com.wb.skincare.netwarks.NetworkResult
import com.wb.skincare.utils.ProgressDialog
import com.wb.skincare.viewModels.ClientViewModel
import com.wb.skincare.viewModels.ProviderViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Provider

@AndroidEntryPoint
class AddProviderFragment : Fragment() {

    private var _binding:FragmentAddProviderBinding?=null
    private val binding get() = _binding!!

    private val providerViewModel by viewModels<ProviderViewModel>()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAddProviderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialData()
    }

    private fun setInitialData() {
        bindObservers()
        clickListener()
        progressDialog = ProgressDialog(requireContext())

    }

    private fun clickListener() {
        binding.AddProviderBtnId.setOnClickListener {
            //val authToken=tokenManager.authToken
            val name = binding.firstNameIEtvId.editText!!.text.toString()


            if (name.isEmpty()) {
                binding.firstNameEtvId.error = "Field can not be empty"
                binding.firstNameEtvId.requestFocus()
                return@setOnClickListener
            }

            val providerRequest= ProviderRequest(name)
            providerViewModel.createProvider(providerRequest)


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
        providerViewModel.createProviderLiveData.observe(viewLifecycleOwner, Observer {

            progressDialog.dismiss()

            when(it){
                is NetworkResult.Success ->{
                    //Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    replaceFragment(ProviderFragment())
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