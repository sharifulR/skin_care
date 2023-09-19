package com.wb.skincare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wb.skincare.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,HomeFragment()).commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeNavId->replaceFragment(HomeFragment())
                R.id.clientNavId->replaceFragment(ClientFragment())
                R.id.serviceNavId->replaceFragment(ServiceCategoryFragment())
                R.id.invoiceNavId->replaceFragment(InvoiceFragment())
                R.id.profileNavId->replaceFragment(ProfileFragment())
                else->{

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction.commit()
    }

}