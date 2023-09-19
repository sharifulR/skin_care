package com.wb.skincare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wb.skincare.databinding.FragmentHomeBinding
import com.wb.view.*


class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.BranchCardId.setOnClickListener {

            replaceFragment(BranchFragment())
        }
        binding.ProviderCardId.setOnClickListener {

            replaceFragment(ProviderFragment())
        }
        binding.PaymentCardId.setOnClickListener {
            replaceFragment(PaymentFragment())
        }
        binding.aboutCard.setOnClickListener {
            /*findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)*/
            replaceFragment(AboutFragment())

        }
        binding.documentCardId.setOnClickListener {
            replaceFragment(DocumentFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction=fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction?.addToBackStack(null)?.commit()
    }


}