package com.wb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.wb.skincare.MainActivity
import com.wb.skincare.R
import com.wb.skincare.databinding.ActivitySplashBinding
import com.wb.skincare.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    lateinit var tokenManager: TokenManager

    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivitySplashBinding.inflate(layoutInflater)


        tokenManager=TokenManager(this)

        Handler().postDelayed({

            if(tokenManager.isLogin!!){
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP.or(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }else {
                binding.splashLayout.isVisible=false
                val fragmentManager=supportFragmentManager
                val fragmentTransaction=fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container,LoginFragment())
                fragmentTransaction.commit()
            }

        }, DURATION)

        setContentView(binding.root)
    }

    companion object {
        const val DURATION = 2000L
    }
}